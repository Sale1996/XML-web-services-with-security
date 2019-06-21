package com.tim9.accommodationservice.endpoints;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.tim9.accommodationservice.models.Bearer;
import com.tim9.accommodationservice.models.GetTypesRequest;
import com.tim9.accommodationservice.models.GetTypesResponse;
import com.tim9.accommodationservice.services.TypeService;
import com.tim9.accommodationservice.utils.TokenExtractor;

@Endpoint
public class TypeEndpoint {
	
	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private TypeService typeService;
	
	@Autowired
	public TypeEndpoint(TypeService typeService) {
		this.typeService = typeService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypesRequest")
	@ResponsePayload
	public GetTypesResponse getTypes(@SoapHeader("{" + Bearer.AUTH_NS + "}Bearer") SoapHeaderElement bearerToken, @RequestPayload GetTypesRequest request) {
		
		Logger log = Logger.getLogger(TypeEndpoint.class);
		log.error("\n\n\n\n\n\n"+TokenExtractor.getBearerToken(bearerToken));
		
		
		
		GetTypesResponse response = new GetTypesResponse();
		response.setType(typeService.findAllSoap());
		return response;
	}
	
	
}
