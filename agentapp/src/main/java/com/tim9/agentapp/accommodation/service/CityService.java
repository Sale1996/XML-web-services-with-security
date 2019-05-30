package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.CityDTO;
import com.tim9.agentapp.accommodation.model.City;
import com.tim9.agentapp.accommodation.repository.CityRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOCityConverter;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	DTOCityConverter cityConverter;
	
	

	public List<CityDTO> findAll() {
		
		Optional< List<City> > cities = Optional.of( cityRepository.findAll() );
		
		ArrayList < CityDTO > dtoCities = new ArrayList<CityDTO>();
		
		if ( cities.isPresent() ) {
			
			for ( City City : cities.get() ) {
				
				dtoCities.add(cityConverter.convertToDTO(City));
				
			}
			
			return dtoCities;
			
		}
			
		return Collections.emptyList();

		
	}

	public CityDTO findById(Long id) {
		
		Optional< City > city = cityRepository.findById(id);
		
		
		if ( city.isPresent() ) {
			
			return cityConverter.convertToDTO(city.get());
		
		}
		else {
			
			return new CityDTO();
			
		}
		
	}
}
