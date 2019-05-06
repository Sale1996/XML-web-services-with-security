package com.tim9.accommodationservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.dtos.AccommodationDTO;
import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationservice.repository.AccommodationRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationConverter;

@Service
public class AccommodationService {

	@Autowired
	AccommodationRepository accommodationRepository;
	
	@Autowired
	DTOAccommodationConverter accommodationConverter;
	
	
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
		
		Accommodation Accommodation = accommodationRepository.save(accommodationConverter.convertFromDTO(accommodation));
		
		accommodation.setAccommodationId(Accommodation.getAccommodationId());
		
		return accommodation;
	
	}

	public AccommodationDTO update(long id, AccommodationDTO accommodation) {
		
		Optional<Accommodation> accommodationForChange = accommodationRepository.findById(id);
		
		if( accommodationForChange.isPresent() && accommodation!=null ) {
										
			accommodationForChange.get().setDescription(accommodation.getDescription());
			accommodationForChange.get().setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
			accommodationForChange.get().setXCord(accommodation.getXCord());
			accommodationForChange.get().setYCord(accommodation.getYCord());
			//liste jos ne diramo
			
	
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

}
