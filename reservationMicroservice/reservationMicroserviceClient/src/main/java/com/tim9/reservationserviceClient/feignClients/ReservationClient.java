package com.tim9.reservationserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
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
	public List<ReservationDTO> getReservations();
	
	@GetMapping("/{reservationId}")
	public ReservationDTO getReservationById(@PathVariable("reservationId") long id);

	@PostMapping("/")
	public ReservationDTO createReservation(@RequestBody ReservationDTO reservation);

	@PutMapping("/{reservationId}")
	public ReservationDTO updateReservation(@PathVariable("reservationId") long id, @RequestBody ReservationDTO reservation);

	@DeleteMapping("/{reservationId}")	
	public ReservationDTO deleteResrevation(@PathVariable("reservationId") long id);
	
	@PostMapping("/searchFromAccommodations/{dateFrom}/{dateTo}")
	public List<Long> getAccommodationUnitIdsForPeriod(@RequestBody List<Long> accommodationIds, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo);

	@GetMapping("/accommodationClients/{accommodationId}")
	public List<Long> getAccommodationClients(@PathVariable("accommodationId") Long accommodationId);

	@GetMapping("/accommodation/{reservationId}")
	public List<Long> getReservationsByAccommodation(@PathVariable("reservationId") Long reservationId);


	@GetMapping("/activeReservationUnit/{id}")
	public Boolean isThereActiveReservationForUnit(@PathVariable("id") Long id);
	
	@DeleteMapping("/occupanciesByUnit/{id}")
	public Boolean deleteOccuppanciesByUnit(@PathVariable("id") Long id);

}
