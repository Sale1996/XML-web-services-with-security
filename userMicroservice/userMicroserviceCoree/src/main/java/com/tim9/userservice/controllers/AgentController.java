package com.tim9.userservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.userservice.services.AgentService;
import com.tim9.userserviceClient.dtos.AgentDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/agent")
@Api(value="agent")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all agents", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity< List<AgentDTO> > getAgents(){
		
		List<AgentDTO> agents = agentService.findAll();
		
		return (!agents.isEmpty()) ? new ResponseEntity< List<AgentDTO> > (agents, HttpStatus.OK) : new ResponseEntity<List<AgentDTO>>( agents, HttpStatus.OK );
	}
	
	
	@GetMapping("/{agentId}")
	@ApiOperation( value = "Finds one agent by id.", notes = "Returns found agent.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< AgentDTO > getAgentById(@PathVariable("agentId") long id){
		
		AgentDTO agent = agentService.findById(id);
		
		return (agent.getId() != null) ? new ResponseEntity< AgentDTO > (agent, HttpStatus.OK) : new ResponseEntity< AgentDTO >( HttpStatus.NOT_FOUND);
	}
	
 
	
	
	@PostMapping("")
	@ApiOperation( value = "Create an agent.", notes = "Returns the agent being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< AgentDTO > createAgent(@RequestBody AgentDTO agent) {
		
		AgentDTO savedAgent = agentService.save(agent);
		
		return ( savedAgent.getId() != null )? new ResponseEntity< AgentDTO > ( savedAgent, HttpStatus.CREATED ) : new ResponseEntity< AgentDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@PutMapping("/{agentId}")
	@ApiOperation( value= "Change an agent", notes = "Returns the agent being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< AgentDTO > updateAgent(@PathVariable("agentId") long id, @RequestBody AgentDTO agent) { 
		
		AgentDTO agentToUpdate = agentService.update(id, agent);
		
	    return ( agentToUpdate.getId() != null )? new ResponseEntity< AgentDTO > ( agentToUpdate, HttpStatus.OK ) : new ResponseEntity< AgentDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/pass/{agentId}")
	@ApiOperation( value= "Change a password", notes = "Returns the user being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent) { 
		
		Boolean agentToUpdate = agentService.changePassword(id, agent);
		
	    return ( agentToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/status/{agentId}")
	@ApiOperation( value= "Change a status", notes = "Returns the user being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > changeStatusUser(@PathVariable("agentId") long id, @RequestBody AgentDTO agent) { 
		
		Boolean agentToUpdate = agentService.changeStatus(id, agent);
		
	    return ( agentToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > ( HttpStatus.BAD_REQUEST );

	}
		
	@DeleteMapping("/{agentId}")
	@ApiOperation( value = "Delete an agent.", notes = "Returns the agent being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< AgentDTO > deleteAgent(@PathVariable("agentId") long id) {
	
		AgentDTO deletedagent = agentService.delete(id);
		
		return ( deletedagent.getId() != null )? new ResponseEntity< AgentDTO > ( deletedagent, HttpStatus.OK ) : new ResponseEntity< AgentDTO > ( HttpStatus.BAD_REQUEST );

	}
	
}
