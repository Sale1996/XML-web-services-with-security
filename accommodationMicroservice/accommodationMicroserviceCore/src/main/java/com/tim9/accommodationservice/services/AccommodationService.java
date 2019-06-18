package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationservice.models.City;
import com.tim9.accommodationservice.repository.AccommodationRepository;
import com.tim9.accommodationservice.repository.CityRepository;
import com.tim9.accommodationservice.utils.DatasFromReservationMicroservice;
import com.tim9.accommodationservice.utils.DatasFromUserMicroservice;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationConverter;
import com.tim9.accommodationservice.utils.dtoConverters.DTOCityConverter;
import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.SearchDTO;
import com.tim9.userserviceClient.dtos.AgentDTO;

@Service
public class AccommodationService {

	AccommodationRepository accommodationRepository;
	CityRepository  cityRepository;
	
	DTOAccommodationConverter accommodationConverter;
	DTOCityConverter cityConverter;
	
	DatasFromUserMicroservice userMicroservise;
	DatasFromReservationMicroservice reservationMicroservice;
	
	
	public AccommodationService(AccommodationRepository accommodaitonRepository, DTOAccommodationConverter accommodationConverter,
			CityRepository cityRepository, DatasFromUserMicroservice userMicroservice, DTOCityConverter cityConverter, DatasFromReservationMicroservice reservationMicroservice) {

			this.accommodationRepository = accommodaitonRepository;
			this.cityRepository = cityRepository;
			this.accommodationConverter = accommodationConverter;
			this.userMicroservise = userMicroservice;
			this.reservationMicroservice = reservationMicroservice;
			this.cityConverter = cityConverter;
			
	}
	
	
	public List<AccommodationDTO> findAll() {
		
		Optional< List<Accommodation> > accommodations = Optional.of ( accommodationRepository.findAll() );
		
		ArrayList < AccommodationDTO > dtoAccommodations = new ArrayList< AccommodationDTO >();
		
		if ( accommodations.isPresent() ) {
			
			for ( Accommodation candidate : accommodations.get() ) {
				
				dtoAccommodations.add(accommodationConverter.convertToDTO(candidate));
				
			}
			
			return dtoAccommodations;
			
		}
			
		return Collections.emptyList();

		
	}
	
	/*************************************************************/
	
	public List<AccommodationDTO> findAllByCityAndNumberOfGuests(Long city, int numberOfGuests, SearchDTO search) {
		
		/*
		Optional< List<Accommodation> > accommodations = Optional.of ( accommodationRepository.searchAccommodations(city, numberOfGuests) );
		
		ArrayList < AccommodationDTO > dtoAccommodations = new ArrayList< AccommodationDTO >();

		
		if ( accommodations.isPresent() ) {
			for( Accommodation candidate : accommodations.get() ) {
				dtoAccommodations.add(accommodationConverter.convertToDTO(candidate));			
			}

			return dtoAccommodations;
			
		}
			
		return Collections.emptyList();

		*/
		
		// ovde se dobavljaju sve akomodacije koje se nalaze na navedenoj lokaciji
		Optional<List<Long>> accommodationIds = Optional.of(accommodationRepository.findAccommodationIdsByCity(city, search.getDistance()));
		
		// ovde se dobavljaju sve jedinice navedenih akomodacija koje SU ZAUZETE u navedenom periodu
		String dateFrom = search.getDateFrom();
		String dateTo = search.getDateTo();
		List<Long> accommodationUnits = reservationMicroservice.getAccommodationUnitIds(accommodationIds.get(), dateFrom, dateTo);
		
		accommodationUnits.add(-1l);
		
		search.getExtraFields().add(-1l);
		
		// sad treba iz  baze izvuci sve akomodacije cije slobodne jedinice imaju potreban kapacitet
		Optional<List<Accommodation>> accommodations = accommodationRepository.searchAccommodations(accommodationIds.get(), accommodationUnits, numberOfGuests, search.getType(), search.getCategory(), search.getExtraFields(),search.getExtraFields().size()-1);
		ArrayList < AccommodationDTO > dtoAccommodations = new ArrayList< AccommodationDTO >();
		if ( accommodations.isPresent() ) {
			for ( Accommodation candidate : accommodations.get() ) {
				dtoAccommodations.add(accommodationConverter.convertToDTO(candidate));
			}
			return dtoAccommodations;
		}
		return Collections.emptyList();
	}
	
/************************************************************************/
	
	public AccommodationDTO findById(long id) {
		
		Optional< Accommodation > accommodation = accommodationRepository.findById(id);
		
		
		if ( accommodation.isPresent() ) {
			
			return accommodationConverter.convertToDTO(accommodation.get());
		
		}
		else {
			
			return new AccommodationDTO();
			
		}
		
	}

	public List<AccommodationDTO> findByName(String username) {
		return null; //to be implemented...
	}

	public AccommodationDTO save(AccommodationDTO accommodation) {
			
		accommodation.setAccommodationId(-1l);
		
		if(!validateAccommodaiton(accommodation)) {
			return new AccommodationDTO();
		}
		
		Accommodation Accommodation = accommodationConverter.convertFromDTO(accommodation);
		Accommodation.setLastUpdated(LocalDateTime.now());
		Accommodation = accommodationRepository.save(Accommodation);
		
		accommodation.setAccommodationId(Accommodation.getAccommodationId());
		
		return accommodation;
	
	}

	public AccommodationDTO update(long id, AccommodationDTO accommodation) {
		
		Optional<Accommodation> accommodationForChange = accommodationRepository.findById(id);
		
		if( accommodationForChange.isPresent() && accommodation!=null ) {
			
			if(!validateAccommodaiton(accommodation)) {
				return new AccommodationDTO();
			}
										
			accommodationForChange.get().setDescription(accommodation.getDescription());
			accommodationForChange.get().setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
			accommodationForChange.get().setLastUpdated(LocalDateTime.now());
			accommodationForChange.get().setAccommodationName(accommodation.getAccommodationName());
			accommodationForChange.get().setCity(cityConverter.convertFromDTO(accommodation.getCity()));
			
			accommodationRepository.save(accommodationForChange.get());
			
			accommodation.setAccommodationId(accommodationForChange.get().getAccommodationId());
			
			
			return accommodation;
		
		}
		
		return new AccommodationDTO();
	}

	public AccommodationDTO delete(long id) {
		
		Optional<Accommodation> accommodationToDelete = accommodationRepository.findById(id);
		
		if( accommodationToDelete.isPresent() ) {
			
			accommodationRepository.deleteById(id);
			
			return accommodationConverter.convertToDTO(accommodationToDelete.get());
		
		}
		
		return new AccommodationDTO();
		
	}

	public AccommodationDTO findByAgentId(long id) {
		
		Optional< Accommodation > accommodation = accommodationRepository.findOneByAgentId(id);
		
		
		if ( accommodation.isPresent() ) {
			
			return accommodationConverter.convertToDTO(accommodation.get());
		
		}
		else {
			
			return new AccommodationDTO();
			
		}
		
	}
	
	
	private boolean validateAccommodaiton(AccommodationDTO accommodation) {
		
		boolean isValid = true;
		
		
		if(accommodation.getAccommodationName().equals("") || accommodation.getCountedNumberOfBeds()<0 || accommodation.getNumberOfDaysBeforeCancelation()<0) {
			isValid = false;
		}
		
		//checking city's existence
		
		Optional<City> city = cityRepository.findById(accommodation.getCity().getCityId());
		
		if(!city.isPresent()) {
			isValid = false;
		}
		
		//checking existence of agent
		AgentDTO agent = userMicroservise.getAgentById(accommodation.getAgentId());
		
		if(agent.getId()==null) {
			isValid = false;
		}
		
		
		return isValid;
	}

}
