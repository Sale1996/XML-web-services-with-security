package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.Type;
import com.tim9.accommodationservice.repository.TypeRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOTypeConverter;
import com.tim9.accommodationserviceclient.dtos.TypeDTO;

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

	public TypeDTO save(TypeDTO dto) {
		
		//checking if there is already Type with the same name
		Optional< Type > foundType = typeRepository.findByTypeName(dto.getTypeName());
		
		if( foundType.isPresent() ) {
			return new TypeDTO();
		}
		
		dto.setTypeId(-1l);
			
		Type type = typeConverter.convertFromDTO(dto);
		type.setLastUpdated(LocalDateTime.now());
		type = typeRepository.save(type);
		
		dto.setTypeId(type.getTypeId());
		
		return dto;
	}

	public TypeDTO update(Long id, TypeDTO typeDTO) {
		
		Optional< Type > typeForChange = typeRepository.findById(id);
		
		if( typeForChange.isPresent() && typeDTO!=null ) {			
			//checking if there is already Type with the same name but not same id
			Optional<Type> foundType = typeRepository.findByTypeName(typeDTO.getTypeName());
			
			if( foundType.isPresent() && foundType.get().getTypeId() != typeForChange.get().getTypeId() ) {
				return new TypeDTO();
			}
			
			typeForChange.get().setTypeName(typeDTO.getTypeName());
			typeForChange.get().setLastUpdated(LocalDateTime.now());
			
			typeRepository.save(typeForChange.get());
			
			typeDTO.setTypeId(typeForChange.get().getTypeId());
			
			return typeDTO;
		}
		
		return new TypeDTO();
	}

	public TypeDTO delete(Long id) {
		
		Optional< Type > typeToDelete = typeRepository.findById(id);
		
		if( typeToDelete.isPresent() ) {
			
			typeRepository.deleteById(id);
			
			return typeConverter.convertToDTO(typeToDelete.get());
		
		}
		
		return new TypeDTO();
		
	}

}
