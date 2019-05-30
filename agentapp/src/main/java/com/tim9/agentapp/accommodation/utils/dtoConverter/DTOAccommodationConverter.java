package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.AccommodationDTO;
import com.tim9.agentapp.accommodation.dto.PictureDTO;
import com.tim9.agentapp.accommodation.model.Accommodation;
import com.tim9.agentapp.accommodation.model.Picture;
import com.tim9.agentapp.accommodation.repository.AccommodationRepository;


@Component
public class DTOAccommodationConverter {

	@Autowired
	public AccommodationRepository accommodationRepository;
	
	@Autowired 
	public DTOPictureConverter pictureConverter;
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
		
		for(Picture picture : accommodation.getPictures()) {
			dto.getPictures().add(pictureConverter.convertToDTO(picture));
		}
		
		return dto;
		
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
		
		for(PictureDTO picture : dto.getPictures()) {
			newBean.getPictures().add(pictureConverter.convertFromDTO(picture));
		}
		
		return newBean;
		
	}
	
	


}
