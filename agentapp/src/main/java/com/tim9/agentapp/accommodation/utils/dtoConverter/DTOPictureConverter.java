package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.PictureDTO;
import com.tim9.agentapp.accommodation.repository.PictureRepository;
import com.tim9.agentapp.accommodation.wsdl.Picture;


@Component
public class DTOPictureConverter {

	@Autowired
	public PictureRepository pictureRepository;
	
	@Autowired
	public DTOAccommodationConverter accommodationConverter;
	

	
	public PictureDTO convertToDTO (Picture picture) {
		
		PictureDTO dto = new PictureDTO();
		
		dto.setLocalPictureId(picture.getPictureId());
		dto.setPictureId(picture.getPictureId());
		dto.setPicUrl(picture.getPicUrl());
		dto.setAccommodation(accommodationConverter.convertToDTOFromClient(picture.getAccommodation()));
		
		return dto;
		
	}
	
	public Picture convertFromDTO( PictureDTO dto ) {
			
		Picture newCandidate = new Picture();
		
		newCandidate.setPictureId(dto.getPictureId());
		newCandidate.setPicUrl(dto.getPicUrl());
		newCandidate.setAccommodation(accommodationConverter.convertFromDTOToWsdl(dto.getAccommodation()));
		
		return newCandidate;
		
	}
	
	
}
