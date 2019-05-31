package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.Picture;
import com.tim9.accommodationservice.repository.PictureRepository;
import com.tim9.accommodationserviceclient.dtos.PictureDTO;


@Component
public class DTOPictureConverter {

	@Autowired
	public PictureRepository pictureRepository;
	
	@Autowired
	public DTOAccommodationConverter accommodationConverter;
	

	
	public PictureDTO convertToDTO (Picture picture) {
		
		PictureDTO dto = new PictureDTO();
		
		dto.setPictureId(picture.getPictureId());
		dto.setPicUrl(picture.getPicUrl());
		dto.setAccommodation(accommodationConverter.convertToDTO(picture.getAccommodation()));
		
		return dto;
		
	}
	
	public Picture convertFromDTO( PictureDTO dto ) {
		
		Optional<Picture> picture = pictureRepository.findById(dto.getPictureId());
		
		if(picture.isPresent()) {
			
			return picture.get();
			
		}
		
		Picture newCandidate = new Picture();
		
		newCandidate.setPictureId(dto.getPictureId());
		newCandidate.setPicUrl(dto.getPicUrl());
		newCandidate.setAccommodation(accommodationConverter.convertFromDTO(dto.getAccommodation()));
		
		
		return newCandidate;
		
	}
	
	
}
