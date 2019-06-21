package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.CityDTO;
import com.tim9.agentapp.accommodation.model.CityLocal;
import com.tim9.agentapp.accommodation.repository.CityRepository;
import com.tim9.agentapp.accommodation.wsdl.City;

@Component
public class DTOCityConverter {
	
	@Autowired
	public CityRepository cityRepository;
	
	public CityDTO convertToDTO (CityLocal city) {
		
		CityDTO dto = new CityDTO();
		
		dto.setCityId(city.getCityId());
		dto.setxCord(city.getXCord());
		dto.setyCord(city.getYCord());
		dto.setName(city.getName());
		
		return dto;
		
	}
	
	public CityDTO convertToDTOFromClient (City city) {
		
		CityDTO dto = new CityDTO();
		
		dto.setCityId(city.getCityId());
		dto.setxCord(city.getXCord());
		dto.setyCord(city.getYCord());
		dto.setName(city.getName());
		
		return dto;
		
	}
	
	public CityLocal convertFromDTO(CityDTO dto) {
		
		Optional<CityLocal> city = cityRepository.findById(dto.getCityId());
		
		if(city.isPresent()) {
			return city.get();
		}
		
		CityLocal newCity = new CityLocal();
		
		newCity.setName(dto.getName());
		newCity.setXCord(dto.getxCord());
		newCity.setyCord(dto.getyCord());
		newCity.setCityId(-1l);
		
		return newCity;
		
		
		
	}
	
	public City convertFromDTOToWsdl(CityDTO dto) {
		
		City newCity = new City();
		
		newCity.setName(dto.getName());
		newCity.setXCord(dto.getxCord());
		newCity.setYCord(dto.getyCord());
		newCity.setCityId(dto.getCityId());
		
		return newCity;
		
		
		
	}
}
