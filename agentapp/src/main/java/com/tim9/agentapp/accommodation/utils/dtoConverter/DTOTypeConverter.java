package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.model.TypeLocal;
import com.tim9.agentapp.accommodation.wsdl.Type;
import com.tim9.agentapp.accommodation.repository.TypeRepository;


@Component
public class DTOTypeConverter {

	@Autowired
	public TypeRepository typeRepository;
	

	
	public TypeDTO convertToDTO (TypeLocal type) {
		
		TypeDTO dto = new TypeDTO();
		
		dto.setTypeId(type.getTypeId());
		dto.setTypeName(type.getTypeName());
		
		return dto;
		
	}
	
	public TypeDTO convertToDTOFromClient (Type type) {
		
		TypeDTO dto = new TypeDTO();
		
		dto.setTypeId(type.getTypeId());
		dto.setTypeName(type.getTypeName());
		
		return dto;
		
	}
	
	public TypeLocal convertFromDTO( TypeDTO dto ) {
		
		Optional<TypeLocal> type = typeRepository.findById(dto.getTypeId());
		
		if(type.isPresent()) {
			
			return type.get();
			
		}
		
		TypeLocal newCandidate = new TypeLocal();
		
		newCandidate.setTypeId(dto.getTypeId());
		newCandidate.setTypeName(dto.getTypeName());
		
		
		return newCandidate;
		
	}
	
	
}
