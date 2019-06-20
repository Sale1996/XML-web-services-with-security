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

import com.tim9.agentapp.user.dto.MessageDTO;
import com.tim9.agentapp.user.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/message")
@Api(value="message")
@CrossOrigin("http://localhost:4200")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/{messageId}")
	@ApiOperation( value = "Finds one message by id.", notes = "Returns found message.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< MessageDTO > getMessageById(@PathVariable("messageId") long id){
		
		MessageDTO message = messageService.findById(id);
		
		return (message.getMessageId() != null) ? new ResponseEntity< MessageDTO > (message, HttpStatus.OK) : new ResponseEntity< MessageDTO >( HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("")
	@ApiOperation( value = "Gets all messages", notes = "Returns messages ordered by date descending", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< List<MessageDTO> > getAllMessagesOrderedByLatest(){
		
		List<MessageDTO> messages = messageService.getAllMessagesOrderedByLatest();
		
		return (!messages.isEmpty()) ? new ResponseEntity< List<MessageDTO> > (messages, HttpStatus.OK) : new ResponseEntity< List<MessageDTO> >(messages, HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a message.", notes = "Returns the message being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< MessageDTO > createMessage(@RequestBody MessageDTO message) {
		
		MessageDTO savedMessage = messageService.save(message);
		
		return ( savedMessage.getMessageId() != null )? new ResponseEntity< MessageDTO > ( savedMessage, HttpStatus.CREATED ) : new ResponseEntity< MessageDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/{id}")
	@ApiOperation( value = "Opens a message.", notes = "Returns the message being opened.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< MessageDTO > openMessage(@PathVariable("id") long id, @RequestBody MessageDTO message) {
		
		MessageDTO savedMessage = messageService.openMessage(id,message);
		
		return ( savedMessage.getMessageId() != null )? new ResponseEntity< MessageDTO > ( savedMessage, HttpStatus.CREATED ) : new ResponseEntity< MessageDTO > ( HttpStatus.BAD_REQUEST );

	}
	
}
