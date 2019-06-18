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

import com.tim9.accommodationservice.services.AccommodationService;
import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.SearchDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/accommodations")
@Api(value="accommodations")
@CrossOrigin("http://localhost:4200")
public class AccommodationController {

	
	private AccommodationService accommodationService;

	
	
	public AccommodationController(AccommodationService service) {
		this.accommodationService = service;
	}
 

	@GetMapping("")
	@ApiOperation( value = "Returns all accommodations", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<AccommodationDTO> > getAccommodations() {
		
		List< AccommodationDTO > accommodations = accommodationService.findAll();
		
		return ( !accommodations.isEmpty() )? new ResponseEntity< List<AccommodationDTO> > (accommodations, HttpStatus.OK ) : new ResponseEntity<List<AccommodationDTO>>(accommodations, HttpStatus.NOT_FOUND);

	}


	
	@PostMapping("/search/{city}/{numberOfGuest}")
	@ApiOperation( value = "Returns all accommodations by city and number of guests", httpMethod="POST")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<AccommodationDTO> > getAccommodationsByCityAndNumberOfGuests(@PathVariable("city") Long city, @PathVariable("numberOfGuest") int numberOfGuests, @RequestBody SearchDTO search) {
		
		List< AccommodationDTO > accommodations = accommodationService.findAllByCityAndNumberOfGuests(city,numberOfGuests, search);
		
		return ( !accommodations.isEmpty() )? new ResponseEntity< List<AccommodationDTO> > (accommodations, HttpStatus.OK ) : new ResponseEntity<List<AccommodationDTO>>(accommodations, HttpStatus.NOT_FOUND);

	}
	
	

	@GetMapping("/{accommodationId}")
	@ApiOperation( value = "Finds one accommodation by id.", notes = "Returns found accommodation.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<  AccommodationDTO > getAccommodationById(@PathVariable("accommodationId") long id) {
		
		AccommodationDTO accommodation = accommodationService.findById(id);
		
		return ( accommodation.getAccommodationId() != null )? new ResponseEntity< AccommodationDTO > (accommodation, HttpStatus.OK ) : new ResponseEntity<AccommodationDTO>( HttpStatus.NOT_FOUND);
		
	}

	@GetMapping("/agent/{id}")
	@ApiOperation( value = "Finds accommodations by agent username.", notes = "Returns found accommodation.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< AccommodationDTO > getAccommodationsByAgent(@PathVariable Long id) {
		
		AccommodationDTO accommodation = accommodationService.findByAgentId(id);
		
		return ( accommodation.getAccommodationId()!=null )? new ResponseEntity< AccommodationDTO > (accommodation, HttpStatus.OK ) : new ResponseEntity<AccommodationDTO>(accommodation, HttpStatus.NOT_FOUND);

	}

	@PostMapping("")
//	@PreAuthorize("hasAuthority('CREATE_ACCOMMODATION')")
	@ApiOperation( value = "Create an accommodation.", notes = "Returns the accommodation being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< AccommodationDTO > createAccommodation(@RequestBody AccommodationDTO accommodation) {
		
		AccommodationDTO savedAccommodation = accommodationService.save(accommodation);
		
		return ( savedAccommodation.getAccommodationId()!=null )? new ResponseEntity< AccommodationDTO > ( savedAccommodation, HttpStatus.CREATED ) : new ResponseEntity< AccommodationDTO > (savedAccommodation, HttpStatus.BAD_REQUEST );

	}

	@PutMapping("/{accommodationId}")
//	@PreAuthorize("hasAuthority('UPDATE_ACCOMMODATION')")
	@ApiOperation( value= "Change an accommodation", notes = "Returns the accommodation being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< AccommodationDTO > updateAccommodation(@PathVariable("accommodationId") long id, @RequestBody AccommodationDTO accommodation) { //Accommodation treba da bude zapravo AccommodationDTO
	//	String username = SecurityContextHolder.getContext().getAuthentication().getName();

		
		AccommodationDTO accommodationToUpdate = accommodationService.update(id, accommodation);
		
	    return ( accommodationToUpdate.getAccommodationId() != null )? new ResponseEntity< AccommodationDTO > ( accommodationToUpdate, HttpStatus.OK ) : new ResponseEntity< AccommodationDTO > ( HttpStatus.BAD_REQUEST );

	
	}

	@DeleteMapping("/{accommodationId}")
//	@PreAuthorize("hasAuthority('DELETE_ACCOMMODATION')")
	@ApiOperation( value = "Delete an accommodation.", notes = "Returns the accommodation being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< AccommodationDTO > deleteAccommodation(@PathVariable("accommodationId") long id) {
	
		AccommodationDTO deletedAccommodation = accommodationService.delete(id);
		
		if (deletedAccommodation.getAccommodationId() != null)
			return new ResponseEntity< AccommodationDTO > ( deletedAccommodation,HttpStatus.OK );
		else
			return new ResponseEntity< AccommodationDTO > ( HttpStatus.NOT_FOUND );

	}
	

	/*
	 * 
		//	@PostAuthorize("@securityCheck.check(returnObject.getName(),authentication)")

	 * 
	 * */
}
