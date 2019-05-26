package feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dtos.AgentDTO;

@FeignClient(name="agentClients", url = "https://localhost:8081/agent")
public interface AgentClients {
		
	@GetMapping("")
	public ResponseEntity< List<AgentDTO> > getAgents();
		
	@GetMapping("/{agentId}")
	public ResponseEntity< AgentDTO > getAgentById(@PathVariable("agentId") long id);
	
	@PostMapping("")
	public ResponseEntity< AgentDTO > createAgent(@RequestBody AgentDTO agent);
		
	@PutMapping("/{agentId}")
	public ResponseEntity< AgentDTO > updateAgent(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
	
	@PutMapping("/pass/{agentId}")
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
	
	@PutMapping("/status/{agentId}")
	public ResponseEntity< Boolean > changeStatusUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent);
		
	@DeleteMapping("/{agentId}")
	public ResponseEntity< AgentDTO > deleteAgent(@PathVariable("agentId") long id);
	
}
