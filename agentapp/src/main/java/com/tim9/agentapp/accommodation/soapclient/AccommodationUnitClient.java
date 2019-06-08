package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.model.AccommodationUnit;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationUnitRequest;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.DeleteAccommodationUnitRequest;
import com.tim9.agentapp.accommodation.wsdl.DeleteAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationUnitRequest;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationUnitsRequest;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationUnitsResponse;

public class AccommodationUnitClient extends WebServiceGatewaySupport {

	public GetAccommodationUnitsResponse getAccommodationUnits(Long id) {

		GetAccommodationUnitsRequest request = new GetAccommodationUnitsRequest();
		request.setId(id);

		GetAccommodationUnitsResponse response = (GetAccommodationUnitsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreateAccommodationUnitResponse createAccommodationUnit(AccommodationUnit accommodationUnit) {

		CreateAccommodationUnitRequest request = new CreateAccommodationUnitRequest();
		request.setAccommodationUnit(accommodationUnit);

		CreateAccommodationUnitResponse response = (CreateAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public DeleteAccommodationUnitResponse deleteAccommodationUnit(Long id) {

		DeleteAccommodationUnitRequest request = new DeleteAccommodationUnitRequest();
		request.setId(id);

		DeleteAccommodationUnitResponse response = (DeleteAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public EditAccommodationUnitResponse updateAccommodationUnit(AccommodationUnit accommodationUnit) {

		EditAccommodationUnitRequest request = new EditAccommodationUnitRequest();
		request.setAccommodationUnit(accommodationUnit);

		EditAccommodationUnitResponse response = (EditAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
