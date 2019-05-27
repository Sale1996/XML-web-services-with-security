package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.City;
import com.tim9.accommodationservice.repository.CityRepository;
import com.tim9.accommodationserviceclient.dtos.CityDTO;

@Component
public class DTOCityConverter {
	
	@Autowired
	public CityRepository cityRepository;
	
	public CityDTO convertToDTO (City city) {
		
		CityDTO dto = new CityDTO();
		
		dto.setCityId(city.getCityId());
		dto.setxCord(city.getXCord());
		dto.setyCord(city.getYCord());
		dto.setName(city.getName());
		
		return dto;
		
	}
	
	public City convertFromDTO(CityDTO dto) {
		
		Optional<City> city = cityRepository.findById(dto.getCityId());
		
		if(city.isPresent()) {
			return city.get();
		}
		
		City newCity = new City();
		
		newCity.setName(dto.getName());
		newCity.setXCord(dto.getxCord());
		newCity.setyCord(dto.getyCord());
		newCity.setCityId(-1l);
		
		return newCity;
		
		
		
	}
}
