package com.tim9.accommodationservice.controllers;

import java.util.List;

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

import com.tim9.accommodationservice.services.AccommodationUnitService;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/accommodationUnits")
@Api(value="accommodationUnit")
@CrossOrigin(origins = "http://localhost:4200")
public class AccommodationUnitController {

	
	AccommodationUnitService accommodationUnitService;
	
	
	public AccommodationUnitController(AccommodationUnitService service) {
		this.accommodationUnitService = service;
	}
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all accommodationUnits", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<AccommodationUnitDTO> > getAllAccommodationUnits (){
		
		List< AccommodationUnitDTO > accommodationUnits = accommodationUnitService.findAll();
		
		return ( !accommodationUnits.isEmpty() )? new ResponseEntity< List<AccommodationUnitDTO> > ( accommodationUnits, HttpStatus.OK ) : new ResponseEntity< List<AccommodationUnitDTO> > (accommodationUnits, HttpStatus.NOT_FOUND );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one accommodation Unit by id.", notes = "Returns found accommodationUnit.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< AccommodationUnitDTO > getOneAccommodationUnitById (@PathVariable("id") Long id){
		
		AccommodationUnitDTO AccommodationUnitDTO = accommodationUnitService.findById(id);
		
		return ( AccommodationUnitDTO.getAccommodationUnitId()!=null)? new ResponseEntity< AccommodationUnitDTO > ( AccommodationUnitDTO, HttpStatus.OK ) : new ResponseEntity< AccommodationUnitDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a accommodation Unit.", notes = "Returns the accommodation Unit being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< AccommodationUnitDTO > addAccommodationUnit ( @RequestBody AccommodationUnitDTO dto ){
			
		AccommodationUnitDTO savedAccommodationUnit = accommodationUnitService.save(dto);
		
		return ( savedAccommodationUnit!=null )? new ResponseEntity< AccommodationUnitDTO > ( savedAccommodationUnit, HttpStatus.CREATED ) : new ResponseEntity< AccommodationUnitDTO > (savedAccommodationUnit, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a accommodation Unit", notes = "Returns the accommodation Unit being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< AccommodationUnitDTO > updateAccommodationUnit (@PathVariable("id") Long id, @RequestBody AccommodationUnitDTO accommodationUnitDTO ){
			
		AccommodationUnitDTO accommodationUnitToUpdate = accommodationUnitService.update(id, accommodationUnitDTO);
		
	    return ( accommodationUnitToUpdate.getAccommodationUnitId() != null )? new ResponseEntity< AccommodationUnitDTO > ( accommodationUnitToUpdate, HttpStatus.OK ) : new ResponseEntity< AccommodationUnitDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a accommodation Unit.", notes = "Returns the accommodationUnit being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< AccommodationUnitDTO > deleteAccommodationUnit (@PathVariable("id") Long id){
	
		AccommodationUnitDTO deletedAccommodationUnitDTO = accommodationUnitService.delete(id);
		
		return (deletedAccommodationUnitDTO.getAccommodationUnitId() != null ) ? new ResponseEntity< AccommodationUnitDTO > ( deletedAccommodationUnitDTO,HttpStatus.OK ) : new ResponseEntity< AccommodationUnitDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
