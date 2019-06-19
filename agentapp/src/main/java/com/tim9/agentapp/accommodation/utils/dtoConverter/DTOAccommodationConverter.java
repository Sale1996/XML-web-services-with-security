package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.AccommodationDTO;
import com.tim9.agentapp.accommodation.model.AccommodationLocal;
import com.tim9.agentapp.accommodation.repository.AccommodationRepository;
import com.tim9.agentapp.accommodation.wsdl.Accommodation;


@Component
public class DTOAccommodationConverter {

	@Autowired
	public AccommodationRepository accommodationRepository;
	
	@Autowired
	public DTOCityConverter cityConverter;
	
	
	
	public AccommodationDTO convertToDTO (AccommodationLocal accommodation) {
		
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
	
	public AccommodationDTO convertToDTOFromClient (Accommodation accommodation) {
		
		AccommodationDTO dto = new AccommodationDTO();
		
	    dto.setAccommodationId(accommodation.getAccommodationId());
		dto.setDescription(accommodation.getDescription());
		dto.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		dto.setAccommodationName(accommodation.getName());
		dto.setCity(cityConverter.convertToDTOFromClient(accommodation.getCity()));
		dto.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		dto.setAgentId(accommodation.getAgentId());
		
		
		return dto;
		
	}
	
	public  Accommodation convertToWsdl(AccommodationLocal accommodation){
		
		Accommodation wsdl = new Accommodation();
		
		wsdl.setAccommodationId(accommodation.getAccommodationId());
		wsdl.setDescription(accommodation.getDescription());
		wsdl.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		wsdl.setName(accommodation.getAccommodationName());
		//wsdl.setCity(cityConverter.convertToWsdl(accommodation.getCity()));
		wsdl.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		wsdl.setAgentId(accommodation.getAgentId());
			
		return wsdl;
		
	}
	
	public AccommodationLocal convertFromWsdl(com.tim9.agentapp.accommodation.wsdl.Accommodation accommodation) {
				
		AccommodationLocal bean = new AccommodationLocal();
		
		bean.setAccommodationId(accommodation.getAccommodationId());
		bean.setDescription(accommodation.getDescription());
		bean.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		bean.setAccommodationName(accommodation.getName());
		//wsdl.setCity(cityConverter.convertToWsdl(accommodation.getCity()));
		bean.setCountedNumberOfBeds(accommodation.getCountedNumberOfBeds());
		bean.setAgentId(accommodation.getAgentId());
		
		return bean;
	}
	
	public AccommodationLocal convertFromDTO( AccommodationDTO dto ) {
		
		Optional<AccommodationLocal> accommodation = accommodationRepository.findById(dto.getAccommodationId());
		
		if(accommodation.isPresent()) {
			
			return accommodation.get();
			
		}
		
		AccommodationLocal newBean = new AccommodationLocal();
		
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

