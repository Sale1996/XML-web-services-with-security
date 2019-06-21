package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.model.TypeLocal;
import com.tim9.agentapp.accommodation.repository.TypeRepository;
import com.tim9.agentapp.accommodation.soapclient.TypeClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOTypeConverter;
import com.tim9.agentapp.accommodation.wsdl.GetTypesResponse;
import com.tim9.agentapp.accommodation.wsdl.Type;

@Service
public class TypeService {
	
	@Autowired
	TypeClient typeClient;
	
	@Autowired
	DTOTypeConverter typeConverter;
	
	public List<TypeDTO> findAll(String token) {
		
		GetTypesResponse response =  typeClient.GetTypes(token);
		List<TypeDTO> dtoTypes = new ArrayList<TypeDTO>();
		
		if(!response.getType().isEmpty()) {
			
			for ( Type type : response.getType() ) {
				dtoTypes.add(typeConverter.convertToDTOFromClient(type));	
			}
			
			return dtoTypes;
		}
		
		return Collections.emptyList();
	}
	
//	public TypeDTO findById(Long id) {
//		
//		Optional< TypeLocal > type = typeRepository.findById(id);
//				
//		if ( type.isPresent() ) {
//			
//			return typeConverter.convertToDTO(type.get());
//		}
//		else {
//			
//			return new TypeDTO();
//		}
//	}
}
