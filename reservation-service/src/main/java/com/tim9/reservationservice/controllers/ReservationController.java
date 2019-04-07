package com.tim9.reservationservice.controllers;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tim9.reservationservice.models.Accommodation;
import com.tim9.reservationservice.models.Reservation;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate rest;

	@GetMapping("/{reservationId}")
	public Reservation getReservation(@PathVariable("reservationId") long id) {
		
	Accommodation room;
	room = rest.getForObject("https://localhost:8081/accommodations/1", Accommodation.class);
	return new Reservation(1, 1, room.getName(), new Float("100.5"));
	}
}
