package com.tim9.reservationservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tim9.reservationservice.models.Accommodation;
import com.tim9.reservationservice.models.Reservation;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private RestTemplate rest;
	
	@GetMapping("/{reservationId}")
	@PreAuthorize("hasAuthority('READ_RESERVATION')")
	public Reservation getReservation(@PathVariable("reservationId") long id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String authorization = context.getHeader("Authorization");
		String token = "";
		if (authorization != null && authorization.toLowerCase().startsWith("bearer")) {
			token = authorization.substring("Bearer".length()).trim();
			headers.add("Authorization", "Bearer " + token);
		}
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<Accommodation> response = rest.exchange("https://accommodation-service/accommodations/1", HttpMethod.GET, entity, Accommodation.class);
		Accommodation accommodation = response.getBody();
		return new Reservation(1, accommodation.getId(), accommodation.getName(), new Float("100.5"));
		
		
	}
}
