package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.model.ExtraFieldLocal;
import com.tim9.agentapp.accommodation.repository.ExtraFieldRepository;
import com.tim9.agentapp.accommodation.wsdl.ExtraField;


@Component
public class DTOExtraFieldConverter {

	@Autowired
	public ExtraFieldRepository extraFieldRepository;
	
	
	
	public ExtraFieldDTO convertToDTO (ExtraFieldLocal extraField) {
		
		ExtraFieldDTO dto = new ExtraFieldDTO();
		
		dto.setExtraFieldId(extraField.getExtraFieldId());
		dto.setExtraFieldName(extraField.getExtraFieldName());
		dto.setExtraPrice(extraField.getExtraPrice());
		dto.setOptional(extraField.isOptional());
		
		
		return dto;
		
	}
	
	public ExtraFieldDTO convertToDTOFromClient (ExtraField extraField) {
		
		ExtraFieldDTO dto = new ExtraFieldDTO();
		
		dto.setExtraFieldId(extraField.getExtraFieldId());
		dto.setExtraFieldName(extraField.getExtraFieldName());
		dto.setExtraPrice(extraField.getExtraPrice());
		dto.setOptional(extraField.isOptional());
		
		
		return dto;
		
	}
	
	public ExtraFieldLocal convertFromDTO( ExtraFieldDTO dto ) {
		
		Optional<ExtraFieldLocal> extraField = extraFieldRepository.findById(dto.getExtraFieldId());
		
		if(extraField.isPresent()) {
			
			return extraField.get();
			
		}
		
		ExtraFieldLocal newCandidate = new ExtraFieldLocal();
		
		newCandidate.setExtraFieldId(dto.getExtraFieldId());
		newCandidate.setExtraFieldName(dto.getExtraFieldName());
		newCandidate.setExtraPrice(dto.getExtraPrice());
		newCandidate.setOptional(dto.isOptional());
		
		return newCandidate;
		
	}
	
	
}
