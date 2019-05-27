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

import com.tim9.userservice.services.AdminService;

import dtos.AdminDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@Api(value="admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("")
	@ApiOperation( value = "Returns all agents", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )
	public ResponseEntity< List<AdminDTO> > getAdmins(){
		
		List<AdminDTO> admins = adminService.findAll();
		
		return (!admins.isEmpty()) ? new ResponseEntity< List<AdminDTO> > (admins, HttpStatus.OK) : new ResponseEntity<List<AdminDTO>>( admins, HttpStatus.OK );
	}
	
	
	@GetMapping("/{adminId}")
	@ApiOperation( value = "Finds one admin by id.", notes = "Returns found admin.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< AdminDTO > getAdminById(@PathVariable("adminId") long id){
		
		AdminDTO admin = adminService.findById(id);
		
		return (admin.getId() != null) ? new ResponseEntity< AdminDTO > (admin, HttpStatus.OK) : new ResponseEntity< AdminDTO >( HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create an admin.", notes = "Returns the agent being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< AdminDTO > registrationAdmin(@RequestBody AdminDTO admin) {
		
		AdminDTO savedAdmin = adminService.save(admin);
		
		return ( savedAdmin.getId() != null )? new ResponseEntity< AdminDTO > ( savedAdmin, HttpStatus.CREATED ) : new ResponseEntity< AdminDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@PutMapping("/{adminId}")
	@ApiOperation( value= "Change an admin", notes = "Returns the admin being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< AdminDTO > settingsAdmin(@PathVariable("adminId") long id, @RequestBody AdminDTO admin) { 
		
		AdminDTO adminToUpdate = adminService.update(id, admin);
		
	    return ( adminToUpdate.getId() != null )? new ResponseEntity< AdminDTO > ( adminToUpdate, HttpStatus.OK ) : new ResponseEntity< AdminDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	@PutMapping("/pass/{adminId}")
	@ApiOperation( value= "Change a password", notes = "Returns the admin being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("adminId") long id, @RequestBody AdminDTO admin) { 
		
		Boolean adminToUpdate = adminService.changePassword(id, admin);
		
	    return ( adminToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{adminId}")
	@ApiOperation( value = "Delete an admin.", notes = "Returns the admin being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< AdminDTO > deleteAdmin(@PathVariable("adminId") long id) {
	
		AdminDTO deletedAdmin = adminService.delete(id);
		
		return ( deletedAdmin.getId() != null )? new ResponseEntity< AdminDTO > ( deletedAdmin, HttpStatus.OK ) : new ResponseEntity< AdminDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
}
