package com.tim9.accommodationservice;

import javax.net.ssl.SSLContext;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AccommodationServiceApplication {

	@Bean
    public RestTemplate restTemplate() throws Exception {
//        ClassPathResource classPathResource = new ClassPathResource("trustStoreNoReservation.jks");
//        SSLContext sslContext = SSLContexts
//            .custom()
//            .loadTrustMaterial(classPathResource.getFile())
//            .build();
//        final CloseableHttpClient client = HttpClients
//            .custom()
//            .setSSLContext(sslContext)
//            .build();
//        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
		return new RestTemplate();
    }
	
	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl");
		SpringApplication.run(AccommodationServiceApplication.class, args);
	}

}
