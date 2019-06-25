package com.tim9.agentapp.reservation.controller;

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

import com.tim9.agentapp.reservation.dto.ReservationDTO;
import com.tim9.agentapp.reservation.service.ReservationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/reservations")
@Api(value="reservations")
@CrossOrigin("http://localhost:4200")
public class ReservationController {	

	@Autowired
	private ReservationService reservationService;


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
	@ApiOperation( value = "Create an occupancy.", notes = "Returns the reservation (occupancy) being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< ReservationDTO > createOccupancy(@RequestBody ReservationDTO reservation) {
		
		ReservationDTO savedReservation = reservationService.createOccupancy(reservation);
		
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
	
	@PutMapping("/confirm")
	@ApiOperation( value= "Cofirms reservations realization", notes = "Returns boolean", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< Boolean > confirmRealization(@RequestBody long reservationId) { 
		
		Boolean reservationToUpdate = reservationService.confirmRealization(reservationId);
		
	    return ( reservationToUpdate.booleanValue() == true )? new ResponseEntity< Boolean > ( true, HttpStatus.OK ) : new ResponseEntity< Boolean > (false, HttpStatus.BAD_REQUEST );

	}

	@DeleteMapping("/{reservationId}")
	//@PreAuthorize("hasAuthority('DELETE_RESERVATION')")
	@ApiOperation( value = "Delete a occupancy.", notes = "Returns the reservation (occupancy) being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< ReservationDTO > deleteResrevation(@PathVariable("reservationId") long id) {
	
		ReservationDTO deletedResrevation = reservationService.deleteOccupancy(id);
		
		if (deletedResrevation.getReservationId() != null)
			return new ResponseEntity< ReservationDTO > ( deletedResrevation,HttpStatus.OK );
		else
			return new ResponseEntity< ReservationDTO > ( HttpStatus.NOT_FOUND );
	}
	
	@GetMapping("/occupancy/{id}")
	@ApiOperation( value = "Returns all occupancies of specific unit", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<ReservationDTO> > getOccupancies(@PathVariable("id") Long id) {
		
		List< ReservationDTO > reservations = reservationService.getOcupancies(id);
		
		return ( !reservations.isEmpty() )? new ResponseEntity< List<ReservationDTO> > (reservations, HttpStatus.OK ) : new ResponseEntity<List<ReservationDTO>>( HttpStatus.NOT_FOUND);
	}
	
}
