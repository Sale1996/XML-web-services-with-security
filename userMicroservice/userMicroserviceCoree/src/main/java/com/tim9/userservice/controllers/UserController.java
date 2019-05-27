package com.tim9.userservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.userservice.services.UserService;

import dtos.UserDTO;
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
	
	
	@PostMapping("")
	@ApiOperation( value = "Create an user.", notes = "Returns the user being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< UserDTO > registrationUser(@RequestBody UserDTO agent) {
		
		UserDTO savedUser = userService.save(agent);
		
		return ( savedUser.getId() != null )? new ResponseEntity< UserDTO > ( savedUser, HttpStatus.CREATED ) : new ResponseEntity< UserDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@PutMapping("/{userId}")
	@ApiOperation( value= "Change an user", notes = "Returns the user being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< UserDTO > settingsUser(@PathVariable("userId") long id, @RequestBody UserDTO user) { 
		
		UserDTO userToUpdate = userService.update(id, user);
		
	    return ( userToUpdate.getId() != null )? new ResponseEntity< UserDTO > ( userToUpdate, HttpStatus.OK ) : new ResponseEntity< UserDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/pass/{userId}")
	@ApiOperation( value= "Change a password", notes = "Returns the user being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("userId") long id, @RequestBody UserDTO user) { 
		
		Boolean userToUpdate = userService.changePassword(id, user);
		
	    return ( userToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/status/{userId}")
	@ApiOperation( value= "Change a status", notes = "Returns the user being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > changeStatusUser(@PathVariable("userId") long id, @RequestBody UserDTO user) { 
		
		Boolean userToUpdate = userService.changeStatus(id, user);
		
	    return ( userToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{userId}")
	@ApiOperation( value = "Delete an user.", notes = "Returns the user being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< UserDTO > deleteUser(@PathVariable("userId") long id) {
	
		UserDTO deletedUser = userService.delete(id);
		
		return ( deletedUser.getId() != null )? new ResponseEntity< UserDTO > ( deletedUser, HttpStatus.OK ) : new ResponseEntity< UserDTO > ( HttpStatus.BAD_REQUEST );

	}
	
}
