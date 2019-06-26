package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.AccommodationUnitDTO;
import com.tim9.agentapp.accommodation.dto.PictureDTO;
import com.tim9.agentapp.accommodation.repository.PictureRepository;
import com.tim9.agentapp.accommodation.soapclient.PictureClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOPictureConverter;
import com.tim9.agentapp.accommodation.wsdl.CreatePictureResponse;
import com.tim9.agentapp.accommodation.wsdl.DeleteAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.DeletePictureResponse;
import com.tim9.agentapp.accommodation.wsdl.GetPicturesResponse;
import com.tim9.agentapp.accommodation.wsdl.Picture;

@Service
public class PictureService {
	
	
	PictureRepository pictureRepository;
	
	
	DTOPictureConverter pictureConverter;
	
	PictureClient pictureClient;
	
	
	AccommodationService accommodationService;

	
	
	
	public PictureService(PictureRepository pictureRepository, DTOPictureConverter pictureConverter, PictureClient pictureClient,AccommodationService accommodationService) {
		this.pictureConverter = pictureConverter;
		this.pictureRepository = pictureRepository;
		this.pictureClient = pictureClient;
		this.accommodationService = accommodationService;
	}
	
	
	

	public List<PictureDTO> findAll(Long accommodaitonId) {
		
		/*
		 * Optional< List<Picture> > pictures = Optional.of( pictureRepository.findAll()
		 * );
		 * 
		 * ArrayList < PictureDTO > dtoPictures = new ArrayList<PictureDTO>();
		 * 
		 * if ( pictures.isPresent() ) {
		 * 
		 * for ( Picture picture : pictures.get() ) {
		 * 
		 * dtoPictures.add(pictureConverter.convertToDTO(picture));
		 * 
		 * }
		 * 
		 * return dtoPictures;
		 * 
		 * }
		 * 
		 * return Collections.emptyList();
		 */
		
		GetPicturesResponse response = pictureClient.GetPicturess(accommodaitonId);
		
		List<PictureDTO> dtoPictures = new ArrayList<PictureDTO>();
		
		if(!response.getPicture().isEmpty()) {
			for(Picture picture : response.getPicture()) {
				dtoPictures.add(pictureConverter.convertToDTO(picture));
			}
			
			return dtoPictures;
		}
		
		
	}

	public PictureDTO findById(Long id) {
		
			return new PictureDTO();		
		
	}

	public PictureDTO save(PictureDTO dto) {
		
		
		/*
		 * dto.setPictureId(-1l);
		 * 
		 * Picture picture = pictureConverter.convertFromDTO(dto); picture =
		 * pictureRepository.save(picture);
		 * 
		 * dto.setPictureId(picture.getPictureId());
		 * 
		 * return dto;
		 */
		
		dto.setPictureId(-1l);
		dto.setAccommodation(accommodationService.findById("token", dto.getAccommodation().getAccommodationId()));
		
		Picture picture = pictureConverter.convertFromDTO(dto);
		
		CreatePictureResponse response = pictureClient.createPicture(picture);
		
		if(response.getPicture().getPictureId() != null) {
			return pictureConverter.convertToDTO(response.getPicture());
		}
		
		return new PictureDTO();
		
	}

	public PictureDTO update(Long id, PictureDTO pictureDTO) {
		

		return new PictureDTO();
	}

	public PictureDTO delete(Long id) {
		
		/*
		 * Optional< Picture > pictureToDelete = pictureRepository.findById(id);
		 * 
		 * if( pictureToDelete.isPresent() ) {
		 * 
		 * pictureRepository.deleteById(id);
		 * 
		 * return pictureConverter.convertToDTO(pictureToDelete.get());
		 * 
		 * }
		 * 
		 * return new PictureDTO();
		 */
		
		DeletePictureResponse response = pictureClient.deletePicture(id);
		
		if(response.getPicture().getPictureId() != null) {
			return pictureConverter.convertToDTO(response.getPicture());
		}
		
		return new PictureDTO();
		
	}
	


}
