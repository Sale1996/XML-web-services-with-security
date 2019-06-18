package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.model.ExtraFieldLocal;
import com.tim9.agentapp.accommodation.soapclient.ExtraFieldClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOExtraFieldConverter;
import com.tim9.agentapp.accommodation.wsdl.ExtraField;
import com.tim9.agentapp.accommodation.wsdl.GetExtraFieldsResponse;

@Service
public class ExtraFieldService {
	
	@Autowired
	ExtraFieldClient extraFieldClient;
	
	@Autowired
	DTOExtraFieldConverter extraFieldConverter;
	
	

	public List<ExtraFieldDTO> findAll() {
		
		GetExtraFieldsResponse response =  extraFieldClient.getExtraFields();
		List<ExtraFieldDTO> dtoExtraFields = new ArrayList<ExtraFieldDTO>();
		
		if(!response.getExtraFields().isEmpty()) {
			
			for ( ExtraField extraField : response.getExtraFields() ) {
				dtoExtraFields.add(extraFieldConverter.convertToDTOFromClient(extraField));	
			}
			
			return dtoExtraFields;
		}
		
		return Collections.emptyList();

		
	}

//	public ExtraFieldDTO findById(Long id) {
//		
//		Optional< ExtraFieldLocal > extraField = extraFieldRepository.findById(id);
//		
//		
//		if ( extraField.isPresent() ) {
//			
//			return extraFieldConverter.convertToDTO(extraField.get());
//		
//		}
//		else {
//			
//			return new ExtraFieldDTO();
//			
//		}
//		
//	}
}
