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


@FeignClient(name="accommodationClient", url = "http://localhost:8762/accommodation/accommodations")
public interface AccommodationClient {


	@GetMapping("")
	public List<AccommodationDTO>  getAccommodations();
	
	
	@GetMapping("/search/{city}/{numberOfGuest}")
	public List<AccommodationSearchDTO> getAccommodationsByCityAndNumberOfGuests(@PathVariable("city") Long city, @PathVariable("numberOfGuest") int numberOfGuests);
	
	

	@GetMapping("/{accommodationId}")
	public AccommodationDTO getAccommodationById(@PathVariable("accommodationId") long id);
	
	
	@GetMapping("/agent/{username}")
	public List<AccommodationDTO> getAccommodationsByAgent(@PathVariable String username);

	@PostMapping("")
	public AccommodationDTO createAccommodation(@RequestBody AccommodationDTO accommodation);

	@PutMapping("/{accommodationId}")
	public AccommodationDTO updateAccommodation(@PathVariable("accommodationId") long id, @RequestBody AccommodationDTO accommodation);

	@DeleteMapping("/{accommodationId}")
	public AccommodationDTO deleteAccommodation(@PathVariable("accommodationId") long id);
	

}
