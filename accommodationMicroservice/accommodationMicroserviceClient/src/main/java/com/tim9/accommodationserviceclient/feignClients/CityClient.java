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

import com.tim9.accommodationserviceclient.dtos.CityDTO;



@FeignClient(name="cityClient", url = "http://localhost:8762/accommodation/cities")
public interface CityClient {
		
	@GetMapping("")
	public List<CityDTO> getAllCities ();
	
	
	@GetMapping("/{id}")
	public CityDTO getOneCityById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public CityDTO addCity ( @RequestBody CityDTO dto );
	
	
	
	@PutMapping("/{id}")
	public CityDTO updateCity (@PathVariable("id") Long id, @RequestBody CityDTO CityDTO );
	
	
	@DeleteMapping("/{id}")
	public CityDTO deleteCity (@PathVariable("id") Long id);
	
}
