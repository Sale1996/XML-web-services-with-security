package com.tim9.agentapp.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tim9.agentapp.reservation.soapclient.ReservationClient;

@Configuration
public class ReservationConfiguration {
	@Bean
	public Jaxb2Marshaller marshallerForReservation() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.tim9.agentapp.reservation.wsdl");
		return marshaller;
	}

	@Bean
	public ReservationClient reservationClient(Jaxb2Marshaller marshallerForReservation) {
		ReservationClient client = new ReservationClient();
		client.setDefaultUri("http://localhost:8762/reservation/ws");
		client.setMarshaller(marshallerForReservation);
		client.setUnmarshaller(marshallerForReservation);
		return client;
	}
}