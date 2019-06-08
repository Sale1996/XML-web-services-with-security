package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.CreatePriceRequest;
import com.tim9.accommodationservice.models.CreatePriceResponse;
import com.tim9.accommodationservice.models.DeletePriceRequest;
import com.tim9.accommodationservice.models.DeletePriceResponse;
import com.tim9.accommodationservice.models.GetPriceRequest;
import com.tim9.accommodationservice.models.GetPriceResponse;
import com.tim9.accommodationservice.services.PriceService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOPriceConverter;

@Endpoint
public class PriceEndpoint {
	
	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private PriceService priceService;
	private DTOPriceConverter dtoPriceConverter;
	
	@Autowired
	public PriceEndpoint(PriceService priceService) {
		this.priceService = priceService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPriceRequest")
	@ResponsePayload
	public GetPriceResponse getPrices(@RequestPayload GetPriceRequest request) {
		GetPriceResponse response = new GetPriceResponse();
		response.setPrice(priceService.findAllSoap());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPriceRequest")
	@ResponsePayload
	public CreatePriceResponse createPrice(@RequestPayload CreatePriceRequest request) {
		CreatePriceResponse response = new CreatePriceResponse();
		response.setPrice(dtoPriceConverter.convertFromDTO(priceService.save(dtoPriceConverter.convertToDTO(request.getPrice()))));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePriceRequest")
	@ResponsePayload
	public DeletePriceResponse getAccommodationUnits(@RequestPayload DeletePriceRequest request) {
		DeletePriceResponse response = new DeletePriceResponse();
		response.setPrice(dtoPriceConverter.convertFromDTO(priceService.delete(request.getId())));
		return response;
	}

}
