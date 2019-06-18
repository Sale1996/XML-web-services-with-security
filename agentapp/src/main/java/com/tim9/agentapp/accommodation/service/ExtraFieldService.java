package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.model.ExtraField;
import com.tim9.agentapp.accommodation.repository.ExtraFieldRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOExtraFieldConverter;

@Service
public class ExtraFieldService {
	
	@Autowired
	ExtraFieldRepository extraFieldRepository;
	
	@Autowired
	DTOExtraFieldConverter extraFieldConverter;
	
	

	public List<ExtraFieldDTO> findAll() {
		
		Optional< List<ExtraField> > extraFields = Optional.of( extraFieldRepository.findAll() );
		
		ArrayList < ExtraFieldDTO > dtoExtraFields = new ArrayList<ExtraFieldDTO>();
		
		if ( extraFields.isPresent() ) {
			
			for ( ExtraField extraField : extraFields.get() ) {
				
				dtoExtraFields.add(extraFieldConverter.convertToDTO(extraField));
				
			}
			
			return dtoExtraFields;
			
		}
			
		return Collections.emptyList();

		
	}

	public ExtraFieldDTO findById(Long id) {
		
		Optional< ExtraField > extraField = extraFieldRepository.findById(id);
		
		
		if ( extraField.isPresent() ) {
			
			return extraFieldConverter.convertToDTO(extraField.get());
		
		}
		else {
			
			return new ExtraFieldDTO();
			
		}
		
	}

	public List<ExtraFieldDTO> findAllByUnit(Long id) {
		
		Optional< List<ExtraField> > extraFields = Optional.of( extraFieldRepository.findAllByAccommodationUnitsLocalAccommodationUnitId(id) );
		
		ArrayList < ExtraFieldDTO > dtoExtraFields = new ArrayList<ExtraFieldDTO>();
		
		if ( extraFields.isPresent() ) {
			
			for ( ExtraField extraField : extraFields.get() ) {
				
				dtoExtraFields.add(extraFieldConverter.convertToDTO(extraField));
				
			}
			
			return dtoExtraFields;
			
		}
			
		return Collections.emptyList();

		
	}
}
