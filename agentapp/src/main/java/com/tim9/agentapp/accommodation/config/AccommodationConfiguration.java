package com.tim9.agentapp.accommodation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import com.tim9.agentapp.accommodation.soapclient.AccommodationClient;
import com.tim9.agentapp.accommodation.soapclient.AccommodationUnitClient;
import com.tim9.agentapp.accommodation.soapclient.CategoryClient;
import com.tim9.agentapp.accommodation.soapclient.CityClient;
import com.tim9.agentapp.accommodation.soapclient.ExtraFieldClient;
import com.tim9.agentapp.accommodation.soapclient.PictureClient;
import com.tim9.agentapp.accommodation.soapclient.PriceClient;
import com.tim9.agentapp.accommodation.soapclient.TypeClient;
import com.tim9.agentapp.utils.HeaderClientInterceptor;

@Configuration
public class AccommodationConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.tim9.agentapp.accommodation.wsdl");
		return marshaller;
	}
	
	@Bean
	  public WebServiceTemplate webServiceTemplate() {
	    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

	    // register the LogHttpHeaderClientInterceptor
	    ClientInterceptor[] interceptors =
	        new ClientInterceptor[] {new HeaderClientInterceptor()};
	    webServiceTemplate.setInterceptors(interceptors);

	    return webServiceTemplate;
	  }

	@Bean
	public AccommodationClient accommodationClient(Jaxb2Marshaller marshaller) {
		AccommodationClient client = new AccommodationClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public AccommodationUnitClient accommodationUnitClient(Jaxb2Marshaller marshaller) {
		AccommodationUnitClient client = new AccommodationUnitClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public CategoryClient categoryClient(Jaxb2Marshaller marshaller) {
		CategoryClient client = new CategoryClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public CityClient cityClient(Jaxb2Marshaller marshaller) {
		CityClient client = new CityClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public ExtraFieldClient extraFieldClient(Jaxb2Marshaller marshaller) {
		ExtraFieldClient client = new ExtraFieldClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public PictureClient pictureClient(Jaxb2Marshaller marshaller) {
		PictureClient client = new PictureClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public PriceClient priceClient(Jaxb2Marshaller marshaller) {
		PriceClient client = new PriceClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	@Bean
	public TypeClient typeClient(Jaxb2Marshaller marshaller) {
		TypeClient client = new TypeClient();
		client.setDefaultUri("http://localhost:8081/accommodationService/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}


}