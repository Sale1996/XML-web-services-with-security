package com.tim9.agentapp.user.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.user.wsdl.Agent;
import com.tim9.agentapp.user.wsdl.GetAgentRequest;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;
import com.tim9.agentapp.user.wsdl.LoginRequest;
import com.tim9.agentapp.user.wsdl.LoginResponse;
import com.tim9.agentapp.user.wsdl.UpdateAgentPasswordRequest;
import com.tim9.agentapp.user.wsdl.UpdateAgentPasswordResponse;
import com.tim9.agentapp.user.wsdl.UpdateAgentRequest;
import com.tim9.agentapp.user.wsdl.UpdateAgentResponse;

public class AgentClient extends WebServiceGatewaySupport {

	public GetAgentResponse getAgent(String email) {

		GetAgentRequest request = new GetAgentRequest();
		request.setEmail(email);

		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public UpdateAgentResponse updateAgent(Agent agent) {

		UpdateAgentRequest request = new UpdateAgentRequest();
		request.setAgent(agent);

		UpdateAgentResponse response = (UpdateAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
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
	
	public LoginResponse login(String email, String password) {

		LoginRequest request = new LoginRequest();
		request.setEmail(email);
		request.setPassword(password);

		LoginResponse response = (LoginResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}

}
