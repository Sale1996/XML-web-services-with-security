package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.City;
import com.tim9.accommodationservice.repository.CityRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOCityConverter;
import com.tim9.accommodationserviceclient.dtos.CityDTO;

@Service
public class CityService {
	
	
	CityRepository cityRepository;
	
	
	DTOCityConverter cityConverter;
	
	
	public CityService(CityRepository cityRepository, DTOCityConverter cityConverter) {
		this.cityConverter = cityConverter;
		this.cityRepository = cityRepository;
	}
	
	

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

	public CityDTO save(CityDTO dto) {
		
		//checking if there is already City with the same name
	
		Optional< City > foundCategory = cityRepository.findByName(dto.getName());
		
		if( foundCategory.isPresent() ) {
			
			return new CityDTO();
		
		}
		
		dto.setCityId(-1l);
			
		City city = cityConverter.convertFromDTO(dto);
		city.setLastUpdated(LocalDateTime.now());
		city = cityRepository.save(city);
		
		dto.setCityId(city.getCityId());
		
		return dto;

}

	public CityDTO update(Long id, CityDTO CityDTO) {
		
		Optional< City > cityForChange = cityRepository.findById(id);
		
		if( cityForChange.isPresent() && CityDTO!=null ) {
			
			//checking if there is already City with the same name but not same id
			
			Optional<City> foundCity = cityRepository.findByName(CityDTO.getName());
			
			if( foundCity.isPresent() && foundCity.get().getCityId() != cityForChange.get().getCityId() ) {
				
				return new CityDTO();
			
			}
										
			cityForChange.get().setName(CityDTO.getName());
			cityForChange.get().setxCord(CityDTO.getxCord());
			cityForChange.get().setYCord(CityDTO.getyCord());
			cityForChange.get().setLastUpdated(LocalDateTime.now());
	
			cityRepository.save(cityForChange.get());
			
			CityDTO.setCityId(cityForChange.get().getCityId());
			
			
			return CityDTO;
		
		}
		
		return new CityDTO();
	}

	public CityDTO delete(Long id) {
		
		Optional< City > cityToDelete = cityRepository.findById(id);
		
		if( cityToDelete.isPresent() ) {
			
			cityRepository.deleteById(id);
			
			return cityConverter.convertToDTO(cityToDelete.get());
		
		}
		
		return new CityDTO();
		
	}

	public List<City> findAllSoap() {
		
		Optional< List<City> > cities = Optional.of( cityRepository.findAll() );
			
		if ( cities.isPresent() ) {
			return cities.get();		
		}
			
		return Collections.emptyList();

		
	}

}
