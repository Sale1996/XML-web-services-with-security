package com.tim9.accommodationservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tim9.accommodationservice.models.Accommodation;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

	@Autowired
	private RestTemplate rest;
 
//	GET metodama mogu pristupati SVI

	@GetMapping("")
	public List<Accommodation> getAccommodations() {
		return null;

	}

	@GetMapping("/{accommodationId}")
	public Accommodation getAccommodationById(@PathVariable("accommodationId") long id) {
		return null;
	}

	@GetMapping("/agent/{username}")
	public List<Accommodation> getAccommodationsByAgent(@PathVariable String username) {


		return null;
	}

	@PostMapping("/")
	@PreAuthorize("hasAuthority('CREATE_ACCOMMODATION')")
	public Accommodation createAccommodation(@RequestBody Accommodation accommodation) { //Accommodation treba da bude zapravo AccommodationDTO
	//	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return null;
	}

	@PutMapping("/")
	@PreAuthorize("hasAuthority('UPDATE_ACCOMMODATION')")
	public Accommodation updateAccommodation(@RequestBody Accommodation accommodation) { //Accommodation treba da bude zapravo AccommodationDTO
	//	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return null;
	}

	@DeleteMapping("/{accommodationId}")
	@PreAuthorize("hasAuthority('DELETE_ACCOMMODATION')")
	public Accommodation deleteAccommodation(@PathVariable("accommodationId") long id) {
		return null;
	}

	/*
	 * 
		//	@PostAuthorize("@securityCheck.check(returnObject.getName(),authentication)")

	 * 
	 * */
}
