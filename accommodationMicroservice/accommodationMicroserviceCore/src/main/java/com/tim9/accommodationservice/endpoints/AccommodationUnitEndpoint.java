package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.AddExtraFieldToUnitRequest;
import com.tim9.accommodationservice.models.AddExtraFieldToUnitResponse;
import com.tim9.accommodationservice.models.CreateAccommodationUnitRequest;
import com.tim9.accommodationservice.models.CreateAccommodationUnitResponse;
import com.tim9.accommodationservice.models.DeleteAccommodationUnitRequest;
import com.tim9.accommodationservice.models.DeleteAccommodationUnitResponse;
import com.tim9.accommodationservice.models.GetAccommodationUnitsRequest;
import com.tim9.accommodationservice.models.GetAccommodationUnitsResponse;
import com.tim9.accommodationservice.models.RemoveExtraFieldFromUnitRequest;
import com.tim9.accommodationservice.models.RemoveExtraFieldFromUnitResponse;
import com.tim9.accommodationservice.models.UpdateAccommodationUnitRequest;
import com.tim9.accommodationservice.models.UpdateAccommodationUnitResponse;
import com.tim9.accommodationservice.services.AccommodationUnitService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationUnitConverter;

@Endpoint
public class AccommodationUnitEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private AccommodationUnitService accommodationUnitService;
	private DTOAccommodationUnitConverter unitConverter;
	
	@Autowired
	public AccommodationUnitEndpoint(AccommodationUnitService accommodationUnitService, DTOAccommodationUnitConverter unitConverter) {
		this.accommodationUnitService = accommodationUnitService;
		this.unitConverter = unitConverter;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccommodationUnitsRequest")
	@ResponsePayload
	public GetAccommodationUnitsResponse getAccommodationUnits(@RequestPayload GetAccommodationUnitsRequest request) {
		GetAccommodationUnitsResponse response = new GetAccommodationUnitsResponse();
		response.setAccommodationUnits(accommodationUnitService.findAllByAccommodationAgentId(request.getId()));
		return response;
	}
	
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAccommodationUnitRequest")
	@ResponsePayload
	public CreateAccommodationUnitResponse createAccommodationUnit(@RequestPayload CreateAccommodationUnitRequest request) {
		CreateAccommodationUnitResponse response = new CreateAccommodationUnitResponse();
		response.setAccommodationUnit(unitConverter.convertFromDTO(accommodationUnitService.save(unitConverter.convertToDTO(request.getAccommodationUnit()))));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAccommodationUnitRequest")
	@ResponsePayload
	public UpdateAccommodationUnitResponse updateAccommodationUnit(@RequestPayload UpdateAccommodationUnitRequest request) {
		UpdateAccommodationUnitResponse response = new UpdateAccommodationUnitResponse();
		response.setAccommodationUnit(unitConverter.convertFromDTO(accommodationUnitService.update(request.getAccommodationUnit().getAccommodationUnitId(),unitConverter.convertToDTO(request.getAccommodationUnit()))));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAccommodationUnitRequest")
	@ResponsePayload
	public DeleteAccommodationUnitResponse getAccommodationUnits(@RequestPayload DeleteAccommodationUnitRequest request) {
		DeleteAccommodationUnitResponse response = new DeleteAccommodationUnitResponse();
		response.setAccommodationUnit(unitConverter.convertFromDTO(accommodationUnitService.delete((request.getId()))));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addExtraFieldToUnitRequest")
	@ResponsePayload
	public AddExtraFieldToUnitResponse addExtraFieldToUnit(@RequestPayload  AddExtraFieldToUnitRequest request) {
		AddExtraFieldToUnitResponse response = new AddExtraFieldToUnitResponse();
		response.setAccommodationUnit(accommodationUnitService.addExtraField(request.getUnitId(), request.getExtraFieldId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeExtraFieldFromUnitRequest")
	@ResponsePayload
	public RemoveExtraFieldFromUnitResponse removeExtraFieldFromUnit(@RequestPayload  RemoveExtraFieldFromUnitRequest request) {
		RemoveExtraFieldFromUnitResponse response = new RemoveExtraFieldFromUnitResponse();
		response.setAccommodationUnit(accommodationUnitService.removeExtraField(request.getUnitId(), request.getExtraFieldId()));
		return response;
	}
}
