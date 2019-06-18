package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.CityDTO;
import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.model.CityLocal;
import com.tim9.agentapp.accommodation.soapclient.CityClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOCityConverter;
import com.tim9.agentapp.accommodation.wsdl.City;
import com.tim9.agentapp.accommodation.wsdl.GetCitiesResponse;
import com.tim9.agentapp.accommodation.wsdl.Type;

@Service
public class CityService {
	
	@Autowired
	CityClient cityClient;
	
	@Autowired
	DTOCityConverter cityConverter;
	
	

	public List<CityDTO> findAll() {
		
		GetCitiesResponse response =  cityClient.getCities();
		List<CityDTO> dtoCities = new ArrayList<CityDTO>();
		
		if(!response.getCities().isEmpty()) {
			
			for ( City city : response.getCities() ) {
				dtoCities.add(cityConverter.convertToDTOFromClient(city));	
			}
			
			return dtoCities;
		}
		
		return Collections.emptyList();
	}

//	public CityDTO findById(Long id) {
//		
//		Optional< CityLocal > city = cityRepository.findById(id);
//		
//		
//		if ( city.isPresent() ) {
//			
//			return cityConverter.convertToDTO(city.get());
//		
//		}
//		else {
//			
//			return new CityDTO();
//			
//		}
//		
//	}
}
