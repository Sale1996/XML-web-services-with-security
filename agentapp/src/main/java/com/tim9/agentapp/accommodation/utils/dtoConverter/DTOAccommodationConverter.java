package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.AccommodationDTO;
import com.tim9.agentapp.accommodation.model.Accommodation;
import com.tim9.agentapp.accommodation.repository.AccommodationRepository;


@Component
public class DTOAccommodationConverter {

	@Autowired
	public AccommodationRepository accommodationRepository;
	
	@Autowired
	public DTOCityConverter cityConverter;
	
	
	
	public AccommodationDTO convertToDTO (Accommodation accommodation) {
		
		AccommodationDTO dto = new AccommodationDTO();
		
	    dto.setLocalAccommodationId(accommodation.getLocalAccommodationId());
		dto.setAccommodationId(accommodation.getAccommodationId());
		dto.setDescription(accommodation.getDescription());
		dto.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		dto.setAccommodationName(accommodation.getAccommodationName());
		dto.setCity(cityConverter.convertToDTO(accommodation.getCity()));
		dto.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		dto.setAgentId(accommodation.getAgentId());
		
		
		return dto;
		
	}
	
	public  com.tim9.agentapp.accommodation.wsdl.Accommodation convertToWsdl(Accommodation accommodation){
		
		com.tim9.agentapp.accommodation.wsdl.Accommodation wsdl = new com.tim9.agentapp.accommodation.wsdl.Accommodation();
		
		wsdl.setAccommodationId(accommodation.getAccommodationId());
		wsdl.setDescription(accommodation.getDescription());
		wsdl.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		wsdl.setName(accommodation.getAccommodationName());
		//wsdl.setCity(cityConverter.convertToWsdl(accommodation.getCity()));
		wsdl.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		wsdl.setAgentId(accommodation.getAgentId());
			
		return wsdl;
		
	}
	
	public Accommodation convertFromWsdl(com.tim9.agentapp.accommodation.wsdl.Accommodation accommodation) {
				
		Accommodation bean = new Accommodation();
		
		bean.setAccommodationId(accommodation.getAccommodationId());
		bean.setDescription(accommodation.getDescription());
		bean.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		bean.setAccommodationName(accommodation.getName());
		//wsdl.setCity(cityConverter.convertToWsdl(accommodation.getCity()));
		bean.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		bean.setAgentId(accommodation.getAgentId());
		
		return bean;
	}
	
	public Accommodation convertFromDTO( AccommodationDTO dto ) {
		
		Optional<Accommodation> accommodation = accommodationRepository.findById(dto.getAccommodationId());
		
		if(accommodation.isPresent()) {
			
			return accommodation.get();
			
		}
		
		Accommodation newBean = new Accommodation();
		
	    newBean.setLocalAccommodationId(dto.getLocalAccommodationId());
		newBean.setAccommodationId(dto.getAccommodationId());
		newBean.setDescription(dto.getDescription());
		newBean.setNumberOfDaysBeforeCancelation(dto.getNumberOfDaysBeforeCancelation());
		newBean.setAccommodationName(dto.getAccommodationName());
		newBean.setCity(cityConverter.convertFromDTO(dto.getCity()));
		newBean.setCountedNumberOfBeds(dto.getCountedNumberOfBeds());
		newBean.setAgentId(dto.getAgentId());
			
		return newBean;
		
	}
}

