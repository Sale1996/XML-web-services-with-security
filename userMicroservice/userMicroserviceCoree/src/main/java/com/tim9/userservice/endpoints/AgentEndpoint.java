package com.tim9.userservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.userservice.models.GetAgentRequest;
import com.tim9.userservice.models.GetAgentResponse;
import com.tim9.userservice.services.AgentService;

@Endpoint
public class AgentEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com";
	
	@Autowired
	private AgentService agentService;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
//		return agentService.findById(request.getId());
		 GetAgentResponse gar =  new GetAgentResponse();
		 gar.setAgent(agentService.findByIdNOTDTO(request.getId()));
		return gar;
	}
}
