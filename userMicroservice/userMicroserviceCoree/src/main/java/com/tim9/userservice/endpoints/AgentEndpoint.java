package com.tim9.userservice.endpoints;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.userservice.models.Agent;
import com.tim9.userservice.models.GetAgentRequest;
import com.tim9.userservice.models.GetAgentResponse;
import com.tim9.userservice.models.LoginRequest;
import com.tim9.userservice.models.LoginResponse;
import com.tim9.userservice.models.UpdateAgentPasswordRequest;
import com.tim9.userservice.models.UpdateAgentPasswordResponse;
import com.tim9.userservice.models.UpdateAgentRequest;
import com.tim9.userservice.models.UpdateAgentResponse;
import com.tim9.userservice.repositories.AgentRepository;
import com.tim9.userservice.services.AgentService;

@Endpoint
public class AgentEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com";
	
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private HttpServletRequest servletRequest;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
//		return agentService.findById(request.getId());
		 GetAgentResponse gar =  new GetAgentResponse();
		 gar.setAgent(agentService.findByEmailNOTDTO(request.getEmail()));
		return gar;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAgentRequest")
	@ResponsePayload
	public UpdateAgentResponse updateAgent(@RequestPayload UpdateAgentRequest request) {
		UpdateAgentResponse response = new UpdateAgentResponse();
		Agent a = request.getAgent();
		response.setAgent(agentService.update(a.getId().longValue(), a));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAgentPasswordRequest")
	@ResponsePayload
	public UpdateAgentPasswordResponse updateAgent(@RequestPayload UpdateAgentPasswordRequest request) {
		UpdateAgentPasswordResponse response = new UpdateAgentPasswordResponse();
		String email = request.getEmail();
		String oldPassword = request.getOldPassword();
		String newPassword = request.getNewPassword();
		response.setSuccess(agentService.changePassword(email, oldPassword, newPassword));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest request) {
		
//		String auth = servletRequest.getHeader("Authorization");
		
		LoginResponse response = new LoginResponse();
		
		String token = agentService.autoLogin(request.getEmail(), request.getPassword());
		
		if(token != "") {
			
			response.setAgent(agentService.findByEmailNOTDTO(request.getEmail()));
			response.setToken(token);
		}
		
		return response;
	}
}
