package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.Picture;
import com.tim9.agentapp.accommodation.wsdl.CreatePictureRequest;
import com.tim9.agentapp.accommodation.wsdl.CreatePictureResponse;
import com.tim9.agentapp.accommodation.wsdl.DeletePictureRequest;
import com.tim9.agentapp.accommodation.wsdl.DeletePictureResponse;
import com.tim9.agentapp.accommodation.wsdl.GetPicturesRequest;
import com.tim9.agentapp.accommodation.wsdl.GetPicturesResponse;

public class PictureClient extends WebServiceGatewaySupport {

	public GetPicturesResponse GetPicturess(Long id) {

		GetPicturesRequest request = new GetPicturesRequest();
		request.setId(id);

		GetPicturesResponse response = (GetPicturesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreatePictureResponse createPicture(Picture picture) {

		CreatePictureRequest request = new CreatePictureRequest();
		request.setPicture(picture);

		CreatePictureResponse response = (CreatePictureResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public DeletePictureResponse deletePicture(Long id) {

		DeletePictureRequest request = new DeletePictureRequest();
		request.setId(id);

		DeletePictureResponse response = (DeletePictureResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
