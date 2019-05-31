package com.tim9.agentapp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.agentapp.user.dto.UserDTO;
import com.tim9.agentapp.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(value="user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	@ApiOperation( value = "Returns all users", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	public ResponseEntity< List<UserDTO> > getUserss(){
		
		List<UserDTO> users = userService.findAll();
		
		return (!users.isEmpty()) ? new ResponseEntity< List<UserDTO> > (users, HttpStatus.OK) : new ResponseEntity<List<UserDTO>>( users, HttpStatus.OK);
	}
	
	
	@GetMapping("/{userId}")
	@ApiOperation( value = "Finds one user by id.", notes = "Returns found user.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< UserDTO > getUserById(@PathVariable("userId") long id){
		
		UserDTO user = userService.findById(id);
		
		return (user.getId() != null) ? new ResponseEntity< UserDTO > (user, HttpStatus.OK) : new ResponseEntity< UserDTO >( HttpStatus.NOT_FOUND);
	}
}
