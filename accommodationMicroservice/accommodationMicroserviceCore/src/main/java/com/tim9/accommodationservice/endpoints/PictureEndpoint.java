package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.CreatePictureRequest;
import com.tim9.accommodationservice.models.CreatePictureResponse;
import com.tim9.accommodationservice.models.DeletePictureRequest;
import com.tim9.accommodationservice.models.DeletePictureResponse;
import com.tim9.accommodationservice.models.GetPicturesRequest;
import com.tim9.accommodationservice.models.GetPicturesResponse;
import com.tim9.accommodationservice.models.Picture;
import com.tim9.accommodationservice.services.PictureService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOPictureConverter;
import com.tim9.accommodationserviceclient.dtos.PictureDTO;

@Endpoint
public class PictureEndpoint {
	
	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private PictureService pictureService;
	private DTOPictureConverter dtoPictureConverter;
	
	@Autowired
	public PictureEndpoint(PictureService pictureService, DTOPictureConverter dtoPictureConverter) {
		this.pictureService = pictureService;
		this.dtoPictureConverter = dtoPictureConverter;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPicturesRequest")
	@ResponsePayload
	public GetPicturesResponse getPictures(@RequestPayload GetPicturesRequest request) {
		GetPicturesResponse response = new GetPicturesResponse();
		response.setPicture(pictureService.findAllSoap(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPictureRequest")
	@ResponsePayload
	public CreatePictureResponse createPicture(@RequestPayload CreatePictureRequest request) {
		CreatePictureResponse response = new CreatePictureResponse();
		Picture picture = request.getPicture();
		PictureDTO pictureDTO = dtoPictureConverter.convertToDTO(picture);
		pictureDTO = pictureService.save(pictureDTO);
		response.setPicture(dtoPictureConverter.convertFromDTO(pictureDTO));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePictureRequest")
	@ResponsePayload
	public DeletePictureResponse getAccommodationUnits(@RequestPayload DeletePictureRequest request) {
		DeletePictureResponse response = new DeletePictureResponse();
		response.setPicture(dtoPictureConverter.convertFromDTO(pictureService.delete(request.getId())));
		return response;
	}
	
}
