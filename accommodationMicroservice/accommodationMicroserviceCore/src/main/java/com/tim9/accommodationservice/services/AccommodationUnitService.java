package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.AccommodationUnit;
import com.tim9.accommodationservice.repository.AccommodationUnitRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationUnitConverter;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;

@Service
public class AccommodationUnitService {
	
	@Autowired
	AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	DTOAccommodationUnitConverter accommodationUnitConverter;

	
	
	public List<AccommodationUnitDTO> findAll() {
		
		Optional< List<AccommodationUnit> > accommodationUnits = Optional.of ( accommodationUnitRepository.findAll() );
		
		ArrayList < AccommodationUnitDTO > dtoAccommodationUnits = new ArrayList< AccommodationUnitDTO >();
		
		if ( accommodationUnits.isPresent() ) {
			
			for ( AccommodationUnit units : accommodationUnits.get() ) {
				
				dtoAccommodationUnits.add(accommodationUnitConverter.convertToDTO(units));
				
			}
			
			return dtoAccommodationUnits;
			
		}
			
		return Collections.emptyList();

		
	}

	public AccommodationUnitDTO findById(Long id) {
		
		Optional< AccommodationUnit > accommodationUnit = accommodationUnitRepository.findById(id);
		
		
		if ( accommodationUnit.isPresent() ) {
			
			return accommodationUnitConverter.convertToDTO(accommodationUnit.get());
		
		}
		else {
			
			return new AccommodationUnitDTO();
			
		}
		
	}

	public AccommodationUnitDTO save(AccommodationUnitDTO dto) {
			
		dto.setAccommodationUnitId(-1l);
		
		AccommodationUnit AccommodationUnit = accommodationUnitConverter.convertFromDTO(dto);
		AccommodationUnit.setLastUpdated(LocalDateTime.now());
		AccommodationUnit = accommodationUnitRepository.save(AccommodationUnit);
		
		dto.setAccommodationUnitId(AccommodationUnit.getAccommodationUnitId());
		
		return dto;
	
	}

	public AccommodationUnitDTO update(Long id, AccommodationUnitDTO accommodationUnitDTO) {
		
		Optional<AccommodationUnit> accommodationUnitForChange = accommodationUnitRepository.findById(id);
		
		if( accommodationUnitForChange.isPresent() && accommodationUnitDTO!=null ) {
													
			accommodationUnitForChange.get().setNumberOfPeople(accommodationUnitDTO.getNumberOfPeople());
			accommodationUnitForChange.get().setLastUpdated(LocalDateTime.now());
			//accommodationUnitForChange.get().setCategory();
			//accommodationUnitForChange.get().setType(value); to be implemented...
			//i jos ima lista koje treba prekopirati ukoliko trbea

	
			accommodationUnitRepository.save(accommodationUnitForChange.get());
			
			accommodationUnitDTO.setAccommodationUnitId(accommodationUnitForChange.get().getAccommodationUnitId());
			
			
			return accommodationUnitDTO;
		
		}
		
		return new AccommodationUnitDTO();
	}

	public AccommodationUnitDTO delete(Long id) {
		
		Optional<AccommodationUnit> accommodationUnitToDelete = accommodationUnitRepository.findById(id);
		
		if( accommodationUnitToDelete.isPresent() ) {
			
			accommodationUnitRepository.deleteById(id);
			
			return accommodationUnitConverter.convertToDTO(accommodationUnitToDelete.get());
		
		}
		
		return new AccommodationUnitDTO();
		
	}

	public List<AccommodationUnit> findAllByAccommodationAgentId(long id) {
		
		Optional< List<AccommodationUnit> > accommodationUnits = Optional.of ( accommodationUnitRepository.findAllByAccommodationAgentId(id) );
		
		
		if ( accommodationUnits.isPresent() ) {
			
			return accommodationUnits.get();
			
		}
			
		return Collections.emptyList();

		
	}

}
