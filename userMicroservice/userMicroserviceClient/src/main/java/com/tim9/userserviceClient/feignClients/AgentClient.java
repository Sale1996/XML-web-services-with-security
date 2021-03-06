package com.tim9.userserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tim9.userserviceClient.dtos.AgentDTO;

@FeignClient(name="agentClient", url = "http://localhost:8762/user/agent")
public interface AgentClient {
		
	@GetMapping("")
	public List<AgentDTO> getAgents();
		
	@GetMapping("/{agentId}")
	public AgentDTO getAgentById(@RequestHeader("Authorization") String tokenString,@PathVariable("agentId") long id);
	
	@GetMapping("email/{email}")
	public AgentDTO getAgentByEmail(@PathVariable("email") String email);
	
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
