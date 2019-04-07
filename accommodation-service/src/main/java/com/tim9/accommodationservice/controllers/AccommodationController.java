package com.tim9.accommodationservice.controllers;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tim9.accommodationservice.models.Accommodation;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {
	
	@Autowired
	private RestTemplate rest;
	
	@GetMapping("/{accommodationId}")
	public Accommodation getAccommodation(@PathVariable("accommodationId") long id) {
		return new Accommodation(1, "Velika Soba 69");
	}
}
