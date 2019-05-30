package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.Picture;
import com.tim9.accommodationservice.repository.PictureRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOPictureConverter;
import com.tim9.accommodationserviceclient.dtos.PictureDTO;

@Service
public class PictureService {
	
	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	DTOPictureConverter pictureConverter;

	public List<PictureDTO> findAll() {
		
		Optional< List<Picture> > pictures = Optional.of( pictureRepository.findAll() );
		
		ArrayList < PictureDTO > dtoPictures = new ArrayList<PictureDTO>();
		
		if ( pictures.isPresent() ) {
			
			for ( Picture picture : pictures.get() ) {
				
				dtoPictures.add(pictureConverter.convertToDTO(picture));
				
			}
			
			return dtoPictures;
			
		}
			
		return Collections.emptyList();

		
	}

	public PictureDTO findById(Long id) {
		
		Optional< Picture > picture = pictureRepository.findById(id);
		
		
		if ( picture.isPresent() ) {
			
			return pictureConverter.convertToDTO(picture.get());
		
		}
		else {
			
			return new PictureDTO();
			
		}
		
	}

	public PictureDTO save(PictureDTO dto) {
		
		
		dto.setPictureId(-1l);
			
		Picture picture = pictureConverter.convertFromDTO(dto);
		picture.setLastUpdated(LocalDateTime.now());
		picture = pictureRepository.save(picture);
		
		dto.setPictureId(picture.getPictureId());
		
		return dto;

	}

	public PictureDTO update(Long id, PictureDTO pictureDTO) {
		
		Optional< Picture > pictureForChange = pictureRepository.findById(id);
		
		if( pictureForChange.isPresent() && pictureDTO!=null ) {
								
			pictureForChange.get().setPicUrl(pictureDTO.getPicUrl());
			pictureForChange.get().setLastUpdated(LocalDateTime.now());
	
			pictureRepository.save(pictureForChange.get());
			
			pictureDTO.setPictureId(pictureForChange.get().getPictureId());
			
			
			return pictureDTO;
		
		}
		
		return new PictureDTO();
	}

	public PictureDTO delete(Long id) {
		
		Optional< Picture > pictureToDelete = pictureRepository.findById(id);
		
		if( pictureToDelete.isPresent() ) {
			
			pictureRepository.deleteById(id);
			
			return pictureConverter.convertToDTO(pictureToDelete.get());
		
		}
		
		return new PictureDTO();
		
	}

}
