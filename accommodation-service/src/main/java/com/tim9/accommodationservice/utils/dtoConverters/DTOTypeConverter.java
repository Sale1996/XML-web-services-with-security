package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.dtos.TypeDTO;
import com.tim9.accommodationservice.models.Type;
import com.tim9.accommodationservice.repository.TypeRepository;


@Component
public class DTOTypeConverter {

	@Autowired
	public TypeRepository typeRepository;
	

	
	public TypeDTO convertToDTO (Type type) {
		
		TypeDTO dto = new TypeDTO();
		
		dto.setTypeId(type.getTypeId());
		dto.setTypeName(type.getTypeName());
		
		return dto;
		
	}
	
	public Type convertFromDTO( TypeDTO dto ) {
		
		Optional<Type> type = typeRepository.findById(dto.getTypeId());
		
		if(type.isPresent()) {
			
			return type.get();
			
		}
		
		Type newCandidate = new Type();
		
		newCandidate.setTypeId(dto.getTypeId());
		newCandidate.setTypeName(dto.getTypeName());
		
		
		return newCandidate;
		
	}
	
	
}
