package com.tim9.userserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.userserviceClient.dtos.AgentDTO;

@FeignClient(name="agentClient", url = "http://localhost:8080/agent")
public interface AgentClient {
		
	@GetMapping("")
	public List<AgentDTO> getAgents();
		
	@GetMapping("/{agentId}")
	public AgentDTO getAgentById(@PathVariable("agentId") long id);
	
	@PostMapping("")
	public AgentDTO createAgent(@RequestBody AgentDTO agent);
		
	@PutMapping("/{agentId}")
	public AgentDTO updateAgent(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
	
	@PutMapping("/pass/{agentId}")
	public Boolean changePasswordUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
	
	@PutMapping("/status/{agentId}")
	public Boolean changeStatusUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
		
	@DeleteMapping("/{agentId}")
	public AgentDTO deleteAgent(@PathVariable("agentId") long id);
	
}
