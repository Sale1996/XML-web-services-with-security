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

		List<Accommodation> accommodations = new ArrayList<>();
		accommodations.add(new Accommodation(1, "Velika Soba 69", "agent"));
		accommodations.add(new Accommodation(2, "Mala Soba 14", "Nikola"));
		accommodations.add(new Accommodation(3, "Srednja Soba 36", "Perisic"));

		return accommodations;
	}

	@GetMapping("/{accommodationId}")
	public Accommodation getAccommodationById(@PathVariable("accommodationId") long id) {
		return new Accommodation(1, "Velika Soba 69", "admin");
	}

	@GetMapping("/agent/{username}")
	public List<Accommodation> getAccommodationsByAgent(@PathVariable String username) {

		// pozvati bazu da pronadje sve akomodacije kojima je vlasnik korisnik sa prosledjenim "username"-om
		List<Accommodation> accommodations = new ArrayList<>();
		Accommodation acc1 = new Accommodation(2, "Mala Soba 14", "nikola");
		accommodations.add(acc1);

		return accommodations;
	}

	@PostMapping("/")
	@PreAuthorize("hasAuthority('CREATE_ACCOMMODATION')")
	public Accommodation createAccommodation(@RequestBody Accommodation accommodation) { //Accommodation treba da bude zapravo AccommodationDTO
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new Accommodation(1, accommodation.getName(), username);
	}

	@PutMapping("/")
	@PreAuthorize("hasAuthority('UPDATE_ACCOMMODATION')")
	public Accommodation updateAccommodation(@RequestBody Accommodation accommodation) { //Accommodation treba da bude zapravo AccommodationDTO
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new Accommodation(1, accommodation.getName(), username);
	}

	@DeleteMapping("/{accommodationId}")
	@PreAuthorize("hasAuthority('DELETE_ACCOMMODATION')")
	public Accommodation deleteAccommodation(@PathVariable("accommodationId") long id) {
		return new Accommodation(1, "Velika Soba 69", "admin");
	}

	/*
	 * 
		//	@PostAuthorize("@securityCheck.check(returnObject.getName(),authentication)")

	 * 
	 * */
}
