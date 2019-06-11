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

import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;


@FeignClient(name="accommodationUnitClient", url = "http://localhost:8081/accommodationUnits")
public interface AccommodationUnitClient {

	
	@GetMapping("")
	public ResponseEntity< List<AccommodationUnitDTO> > getAllAccommodationUnits ();
	
	
	@GetMapping("/{id}")
	public ResponseEntity< AccommodationUnitDTO > getOneAccommodationUnitById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ResponseEntity< AccommodationUnitDTO > addAccommodationUnit ( @RequestBody AccommodationUnitDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity< AccommodationUnitDTO > updateAccommodationUnit (@PathVariable("id") Long id, @RequestBody AccommodationUnitDTO accommodationUnitDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< AccommodationUnitDTO > deleteAccommodationUnit (@PathVariable("id") Long id);
	
}
