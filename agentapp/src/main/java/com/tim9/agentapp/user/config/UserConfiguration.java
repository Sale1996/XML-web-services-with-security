package com.tim9.agentapp.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tim9.agentapp.user.soapclient.AgentClient;
import com.tim9.agentapp.user.soapclient.MessageClient;

@Configuration
public class UserConfiguration {
	@Bean
	public Jaxb2Marshaller marshallerForUser() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.tim9.agentapp.user.wsdl");
		return marshaller;
	}

	@Bean
	public AgentClient agentClient(Jaxb2Marshaller marshallerForUser) {
		AgentClient client = new AgentClient();
		client.setDefaultUri("http://localhost:8762/user/ws");
		client.setMarshaller(marshallerForUser);
		client.setUnmarshaller(marshallerForUser);
		return client;
	}
	
	@Bean
	public MessageClient messageClient(Jaxb2Marshaller marshallerForUser) {
		MessageClient client = new MessageClient();
		client.setDefaultUri("http://localhost:8762/user/ws");
		client.setMarshaller(marshallerForUser);
		client.setUnmarshaller(marshallerForUser);
		return client;
	}

}