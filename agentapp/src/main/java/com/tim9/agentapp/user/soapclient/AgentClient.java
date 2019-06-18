package com.tim9.agentapp.user.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.user.wsdl.GetAgentRequest;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;
import com.tim9.agentapp.user.wsdl.UpdateAgentPasswordRequest;
import com.tim9.agentapp.user.wsdl.UpdateAgentPasswordResponse;

public class AgentClient extends WebServiceGatewaySupport {

	public GetAgentResponse getAgent(Long id) {

		GetAgentRequest request = new GetAgentRequest();
		request.setId(id);

		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
//	public GetAgentResponse updateAgent(Agent agent) {
//
//		UpdateAgentRequest request = new UpdateAgentRequest();
//		request.setAgent(agent);
//
//		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
//				.marshalSendAndReceive(this.getDefaultUri(), request,
//						new SoapActionCallback(""));
//
//		return response;
//	}
	
	public UpdateAgentPasswordResponse updatePassword(String email, String oldPassword, String newPassword) {

		UpdateAgentPasswordRequest request = new UpdateAgentPasswordRequest();
		request.setEmail(email);
		request.setOldPassword(oldPassword);
		request.setNewPassword(newPassword);

		UpdateAgentPasswordResponse response = (UpdateAgentPasswordResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}

}
