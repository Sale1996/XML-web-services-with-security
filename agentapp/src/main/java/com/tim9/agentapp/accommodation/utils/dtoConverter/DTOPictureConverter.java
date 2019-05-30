package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.PictureDTO;
import com.tim9.agentapp.accommodation.model.Picture;
import com.tim9.agentapp.accommodation.repository.PictureRepository;


@Component
public class DTOPictureConverter {

	@Autowired
	public PictureRepository pictureRepository;
	

	
	public PictureDTO convertToDTO (Picture picture) {
		
		PictureDTO dto = new PictureDTO();
		
		dto.setPictureId(picture.getPictureId());
		dto.setPicUrl(picture.getPicUrl());
		
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
		
		
		return newCandidate;
		
	}
	
	
}
