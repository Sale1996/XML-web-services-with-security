package com.tim9.agentapp.accommodation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.PictureDTO;
import com.tim9.agentapp.accommodation.model.Picture;
import com.tim9.agentapp.accommodation.repository.PictureRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOPictureConverter;

@Service
public class PictureService {
	
	
	PictureRepository pictureRepository;
	
	
	DTOPictureConverter pictureConverter;
	
	
	
	public PictureService(PictureRepository pictureRepository, DTOPictureConverter pictureConverter) {
		this.pictureConverter = pictureConverter;
		this.pictureRepository = pictureRepository;
	}
	
	
	

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
		picture = pictureRepository.save(picture);
		
		dto.setPictureId(picture.getPictureId());
		
		return dto;

	}

	public PictureDTO update(Long id, PictureDTO pictureDTO) {
		
		Optional< Picture > pictureForChange = pictureRepository.findById(id);
		
		if( pictureForChange.isPresent() && pictureDTO!=null ) {
								
			pictureForChange.get().setPicUrl(pictureDTO.getPicUrl());
	
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
	
	public List<Picture> findAllSoap() {
		
		Optional< List<Picture> > pictures = Optional.of( pictureRepository.findAll() );
			
		if ( pictures.isPresent() ) {
			return pictures.get();		
		}
			
		return Collections.emptyList();

		
	}

}
