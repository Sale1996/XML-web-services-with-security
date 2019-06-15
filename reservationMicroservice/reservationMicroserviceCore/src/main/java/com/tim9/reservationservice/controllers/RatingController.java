package com.tim9.reservationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tim9.reservationserviceClient.dtos.RatingDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ratings")
@Api(value="ratings")
@CrossOrigin("http://localhost:4200")
public class RatingController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("")
	@ApiOperation( value = "Create a rating.", notes = "Returns the rating being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<String> createRating(@RequestBody RatingDTO rating) {
		
		System.out.println("usao:************************************************");
		//System.out.println("usao:************************************************" + rating.isConfirmed());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
			
		HttpEntity<RatingDTO> entity = new HttpEntity<RatingDTO>(rating, headers);
		
		System.out.println("usao:************************************************" + entity);
		
		String url = "http://localhost:8010/rating-service/us-central1/createRating";
		
		ResponseEntity<String> rest = null;
		
		try {			
			rest = restTemplate.exchange(
	                url,
	                HttpMethod.POST,
	                entity,
	                String.class
	        );
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("*********************" + rest);
		return new ResponseEntity<String>(rest.getBody(), HttpStatus.OK);
	}
	
	@GetMapping("/reservation/{id}")
	@ApiOperation( value = "Finds one rating by reservation id.", notes = "Returns found rating.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<RatingDTO> getRatingByReservationId(@PathVariable("id") Long id) {
		
		String url = "http://localhost:8010/rating-service/us-central1/getRatingByReservationId?id=" + id;
		
			
		ResponseEntity<RatingDTO> response = restTemplate.getForEntity(url, RatingDTO.class);
		
		return new ResponseEntity<RatingDTO>(response.getBody(), HttpStatus.OK);
		
	}
	
	@GetMapping("/accommodation/{id}")
	@ApiOperation( value = "Finds one rating by accommodation id.", notes = "Returns found rating.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<RatingDTO> getRatingByAccommodationId(@PathVariable("id") Long id) {
		
		String url = "http://localhost:8010/rating-service/us-central1/getRatingByAccommodationId?accommodation_id=" + id;
		
		ResponseEntity<RatingDTO> response = restTemplate.getForEntity(url, RatingDTO.class);
		
		return new ResponseEntity<RatingDTO>(response.getBody(), HttpStatus.OK);
		
	}

}
