package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.model.Type;
import com.tim9.agentapp.accommodation.repository.TypeRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOTypeConverter;

@Service
public class TypeService {
	
	@Autowired
	TypeRepository typeRepository;
	
	@Autowired
	DTOTypeConverter typeConverter;
	
	public List<TypeDTO> findAll() {
		
		Optional< List<Type> > types = Optional.of( typeRepository.findAll() );	
		ArrayList < TypeDTO > dtoTypes = new ArrayList<TypeDTO>();
		
		if ( types.isPresent() ) {
			
			for ( Type type : types.get() ) {
				
				dtoTypes.add(typeConverter.convertToDTO(type));	
			}
			
			return dtoTypes;
		}
			
		return Collections.emptyList();	
	}
	
	public TypeDTO findById(Long id) {
		
		Optional< Type > type = typeRepository.findById(id);
				
		if ( type.isPresent() ) {
			
			return typeConverter.convertToDTO(type.get());
		}
		else {
			
			return new TypeDTO();
		}
	}
}
