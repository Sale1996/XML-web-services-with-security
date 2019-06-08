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
import com.tim9.accommodationservice.models.GetPictureRequest;
import com.tim9.accommodationservice.models.GetPictureResponse;
import com.tim9.accommodationservice.services.PictureService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOPictureConverter;

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

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPictureRequest")
	@ResponsePayload
	public GetPictureResponse getPictures(@RequestPayload GetPictureRequest request) {
		GetPictureResponse response = new GetPictureResponse();
		response.setPicture(pictureService.findAllSoap());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPictureRequest")
	@ResponsePayload
	public CreatePictureResponse createPicture(@RequestPayload CreatePictureRequest request) {
		CreatePictureResponse response = new CreatePictureResponse();
		response.setPicture(dtoPictureConverter.convertFromDTO(pictureService.save(dtoPictureConverter.convertToDTO(request.getPicture()))));
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
