package com.tim9.accommodationserviceclient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.AccommodationSearchDTO;


@FeignClient(name="accommodationClient", url = "http://localhost:8081/accommodations")
public interface AccommodationClient {


	@GetMapping("")
	public ResponseEntity< List<AccommodationDTO> > getAccommodations();
	
	
	@GetMapping("/search/{city}/{numberOfGuest}")
	public ResponseEntity< List<AccommodationSearchDTO> > getAccommodationsByCityAndNumberOfGuests(@PathVariable("city") Long city, @PathVariable("numberOfGuest") int numberOfGuests);
	
	

	@GetMapping("/{accommodationId}")
	public ResponseEntity<  AccommodationDTO > getAccommodationById(@PathVariable("accommodationId") long id);
	
	
	@GetMapping("/agent/{username}")
	public ResponseEntity< List<AccommodationDTO> > getAccommodationsByAgent(@PathVariable String username);

	@PostMapping("")
	public ResponseEntity< AccommodationDTO > createAccommodation(@RequestBody AccommodationDTO accommodation);

	@PutMapping("/{accommodationId}")
	public ResponseEntity< AccommodationDTO > updateAccommodation(@PathVariable("accommodationId") long id, @RequestBody AccommodationDTO accommodation);

	@DeleteMapping("/{accommodationId}")
	public ResponseEntity< AccommodationDTO > deleteAccommodation(@PathVariable("accommodationId") long id);
	

}
