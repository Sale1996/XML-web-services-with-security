package com.tim9.pkiapi.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.pkiapi.user.dto.UserDTO;
import com.tim9.pkiapi.user.service.IUserService;



@RestController
@RequestMapping("/pki/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		
		List<UserDTO> users = userService.findAll();
		
		return ( !users.isEmpty() )? new ResponseEntity<List<UserDTO>>(users,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getOneUserById (@PathVariable("id") Long id){
		
		UserDTO userDto = userService.findOneById(id);
		
		return ( userDto.getId()!=null )? new ResponseEntity<UserDTO>(userDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDTO> getOneUserById (@PathVariable("email") String email){
		
		UserDTO userDto = userService.findOneByEmail(email);
		
		return ( userDto.getId()!=null )? new ResponseEntity<UserDTO>(userDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
		
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto){
		
		UserDTO savedUser = userService.save(dto);
		
		return ( savedUser!=null )? new ResponseEntity<UserDTO>(savedUser,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
		UserDTO deletedUser = userService.deleteById(id);
		
		return (deletedUser.getId() != null ) ? new ResponseEntity<UserDTO>(deletedUser,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
