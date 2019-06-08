package com.tim9.agentapp.user.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.user.wsdl.GetAgentRequest;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;

public class AgentClient extends WebServiceGatewaySupport {

	public GetAgentResponse getAgent(Long id) {

		GetAgentRequest request = new GetAgentRequest();
		request.setId(id);

		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}

}
