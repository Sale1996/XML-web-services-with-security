package com.tim9.agentapp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.agentapp.user.dto.AgentDTO;
import com.tim9.agentapp.user.dto.UserDTO;
import com.tim9.agentapp.user.service.AgentService;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/agent")
@Api(value="agent")
@CrossOrigin("http://localhost:4200")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all agents", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )
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
	
	@GetMapping("/update")
	@ApiOperation( value = "Sync Database", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK")})
	public ResponseEntity< Boolean > syncDatabase(){
		return (agentService.syncDatabase()) ? new ResponseEntity< Boolean > (true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
}
