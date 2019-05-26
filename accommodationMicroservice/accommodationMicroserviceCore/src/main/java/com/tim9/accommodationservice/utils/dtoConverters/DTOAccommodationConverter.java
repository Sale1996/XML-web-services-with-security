package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationservice.models.Picture;
import com.tim9.accommodationservice.repository.AccommodationRepository;
import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.PictureDTO;


@Component
public class DTOAccommodationConverter {

	@Autowired
	public AccommodationRepository accommodationRepository;
	
	@Autowired 
	public DTOPictureConverter pictureConverter;
	
	
	
	public AccommodationDTO convertToDTO (Accommodation accommodation) {
		
		AccommodationDTO dto = new AccommodationDTO();
		
		dto.setAccommodationId(accommodation.getAccommodationId());
		dto.setDescription(accommodation.getDescription());
		dto.setNumberOfDaysBeforeCancelation(accommodation.getNumberOfDaysBeforeCancelation());
		dto.setXCord(accommodation.getXCord());
		dto.setYCord(accommodation.getYCord());
		
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
		
		newBean.setAccommodationId(dto.getAccommodationId());
		newBean.setDescription(dto.getDescription());
		newBean.setNumberOfDaysBeforeCancelation(dto.getNumberOfDaysBeforeCancelation());
		newBean.setXCord(dto.getXCord());
		newBean.setYCord(dto.getYCord());
		
		for(PictureDTO picture : dto.getPictures()) {
			newBean.getPictures().add(pictureConverter.convertFromDTO(picture));
		}
		
		return newBean;
		
	}
	
	


}
