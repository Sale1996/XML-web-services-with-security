package com.tim9.reservationserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.reservationserviceClient.dtos.ReservationDTO;


@FeignClient(name="reservationClient", url = "http://localhost:8082/reservations")
public interface ReservationClient {
	

	@GetMapping("")
	public ResponseEntity< List<ReservationDTO> > getReservations();
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<  ReservationDTO > getReservationById(@PathVariable("reservationId") long id);

	@PostMapping("/")
	public ResponseEntity< ReservationDTO > createReservation(@RequestBody ReservationDTO reservation);

	@PutMapping("/{reservationId}")
	public ResponseEntity< ReservationDTO > updateReservation(@PathVariable("reservationId") long id, @RequestBody ReservationDTO reservation);

	@DeleteMapping("/{reservationId}")	
	public ResponseEntity< ReservationDTO > deleteResrevation(@PathVariable("reservationId") long id);
}