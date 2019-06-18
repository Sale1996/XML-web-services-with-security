package com.tim9.reservationservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.client.RestTemplate;

import com.tim9.reservationservice.services.ReservationService;
import com.tim9.reservationserviceClient.dtos.RatingDTO;
import com.tim9.reservationserviceClient.dtos.ReservationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/reservations")
@Api(value="reservations")			//OVO PITAJ SALEA
@CrossOrigin("http://localhost:4200")
public class ReservationController {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private RestTemplate rest;
	*/
	
	/*
	@GetMapping("/{reservationId}")
	@PreAuthorize("hasAuthority('READ_RESERVATION')")
	public Reservation getReservation(@PathVariable("reservationId") long id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String authorization = context.getHeader("Authorization");
		String token = "";
		if (authorization != null && authorization.toLowerCase().startsWith("bearer")) {
			token = authorization.substring("Bearer".length()).trim();
			headers.add("Authorization", "Bearer " + token);
		}
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<Accommodation> response = rest.exchange("https://accommodation-service/accommodations/1", HttpMethod.GET, entity, Accommodation.class);
		Accommodation accommodation = response.getBody();
		return new Reservation(1, accommodation.getId(), accommodation.getName(), new Float("100.5"));
	}
	*/
	
	// get all reservs
	
	// get reserv by id
	
	// update reserv by id
	
	// delete reserv by id
	
	private ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}


	@GetMapping("")
	@ApiOperation( value = "Returns all reservations", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<ReservationDTO> > getReservations() {
		
		List< ReservationDTO > reservations = reservationService.findAll();
		
		return ( !reservations.isEmpty() )? new ResponseEntity< List<ReservationDTO> > (reservations, HttpStatus.OK ) : new ResponseEntity<List<ReservationDTO>>( HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{reservationId}")
	@ApiOperation( value = "Finds one reservation by id.", notes = "Returns found reservation.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<  ReservationDTO > getReservationById(@PathVariable("reservationId") long id) {
		
		ReservationDTO reservation = reservationService.findById(id);
		
		return ( reservation.getReservationId() != null )? new ResponseEntity< ReservationDTO > (reservation, HttpStatus.OK ) : new ResponseEntity<ReservationDTO>( HttpStatus.NOT_FOUND);
	}

	@PostMapping("")
	//@PreAuthorize("hasAuthority('CREATE_RESERVATION')")
	@ApiOperation( value = "Create a reservation.", notes = "Returns the reservation being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< ReservationDTO > createReservation(@RequestBody ReservationDTO reservation) {
		
		System.out.println("**************************************" + reservation.getDateFrom());
		
		ReservationDTO savedReservation = reservationService.save(reservation);
		
		return ( savedReservation!=null )? new ResponseEntity< ReservationDTO > ( savedReservation, HttpStatus.CREATED ) : new ResponseEntity< ReservationDTO > ( HttpStatus.BAD_REQUEST );
	}

	@PutMapping("/{reservationId}")
	//@PreAuthorize("hasAuthority('UPDATE_RESERVATION')")
	@ApiOperation( value= "Change a reservation", notes = "Returns the reservation being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< ReservationDTO > updateReservation(@PathVariable("reservationId") long id, @RequestBody ReservationDTO reservation) {
		
		ReservationDTO reservationToUpdate = reservationService.update(id, reservation);
		
	    return ( reservationToUpdate.getReservationId() != null )? new ResponseEntity< ReservationDTO > ( reservationToUpdate, HttpStatus.OK ) : new ResponseEntity< ReservationDTO > ( HttpStatus.BAD_REQUEST );
	}

	@DeleteMapping("/{reservationId}")
	//@PreAuthorize("hasAuthority('DELETE_RESERVATION')")
	@ApiOperation( value = "Delete a reservation.", notes = "Returns the reservation being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< ReservationDTO > deleteResrevation(@PathVariable("reservationId") long id) {
	
		ReservationDTO deletedResrevation = reservationService.delete(id);
		
		if (deletedResrevation.getReservationId() != null)
			return new ResponseEntity< ReservationDTO > ( deletedResrevation,HttpStatus.OK );
		else
			return new ResponseEntity< ReservationDTO > ( HttpStatus.NOT_FOUND );
	}

	@PostMapping("/searchFromAccommodations/{dateFrom}/{dateTo}")
	//@ApiOperation( value = "Finds one reservation by id.", notes = "Returns found reservation.", httpMethod="GET")
	//@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
	//						 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<Long>> getAccommodationUnitIdsForPeriod(@RequestBody List<Long> accommodationIds, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {
		
		return new ResponseEntity<List<Long>>(reservationService.getAccommodationUnitIdsForPeriod(accommodationIds, dateFrom, dateTo), HttpStatus.OK);
	}

	@GetMapping("/accommodationClients/{accommodationId}")
	//@ApiOperation( value = "Finds one reservation by id.", notes = "Returns found reservation.", httpMethod="GET")
	//@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
	//						 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<Long>> getAccommodationClients(@PathVariable("accommodationId") Long accommodationId) {
		
		return new ResponseEntity<List<Long>>(reservationService.getAccommodationClients(accommodationId), HttpStatus.OK);
	}
	
	@GetMapping("/client/{clientId}")
	@ApiOperation( value = "Returns reservations of a specified client", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<ReservationDTO> > getClientReservations(@PathVariable("clientId") Long clientId) {
		
		List< ReservationDTO > reservations = reservationService.findByClient(clientId);
		
		return ( !reservations.isEmpty() )? new ResponseEntity< List<ReservationDTO> > (reservations, HttpStatus.OK ) : new ResponseEntity<List<ReservationDTO>>( HttpStatus.NOT_FOUND);
	}
}
