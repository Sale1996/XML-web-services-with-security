package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.model.ExtraField;
import com.tim9.agentapp.accommodation.repository.ExtraFieldRepository;


@Component
public class DTOExtraFieldConverter {

	@Autowired
	public ExtraFieldRepository extraFieldRepository;
	
	
	
	public ExtraFieldDTO convertToDTO (ExtraField extraField) {
		
		ExtraFieldDTO dto = new ExtraFieldDTO();
		
		dto.setLocalExtraFieldId(extraField.getLocalExtraFieldId());
		dto.setExtraFieldId(extraField.getExtraFieldId());
		dto.setExtraFieldName(extraField.getExtraFieldName());
		dto.setExtraPrice(extraField.getExtraPrice());
		dto.setOptional(extraField.isOptional());
		
		
		return dto;
		
	}
	
	public ExtraField convertFromDTO( ExtraFieldDTO dto ) {
		
		Optional<ExtraField> extraField = extraFieldRepository.findById(dto.getExtraFieldId());
		
		if(extraField.isPresent()) {
			
			return extraField.get();
			
		}
		
		ExtraField newCandidate = new ExtraField();
		
		newCandidate.setLocalExtraFieldId(dto.getLocalExtraFieldId());
		newCandidate.setExtraFieldId(dto.getExtraFieldId());
		newCandidate.setExtraFieldName(dto.getExtraFieldName());
		newCandidate.setExtraPrice(dto.getExtraPrice());
		newCandidate.setOptional(dto.isOptional());
		
		return newCandidate;
		
	}
	
	
}
