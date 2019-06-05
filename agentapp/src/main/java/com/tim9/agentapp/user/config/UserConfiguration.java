package com.tim9.agentapp.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tim9.agentapp.user.soapclient.UserClient;

@Configuration
public class UserConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.tim9.agentapp.user.wsdl");
		return marshaller;
	}

	@Bean
	public UserClient userClient(Jaxb2Marshaller marshaller) {
		UserClient client = new UserClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}