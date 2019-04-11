package com.tim9.reservationservice;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

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
public class ReservationServiceApplication {
	
	@Bean
    public RestTemplate restTemplate() throws Exception {
        ClassPathResource trustStoreclassPath = new ClassPathResource("trustStore.jks");
        ClassPathResource keyStoreClassPath = new ClassPathResource("keyStore.jks");
        
        //
        KeyStore ks = null;
		try {
			ks = KeyStore.getInstance("JKS");
			InputStream readStream = new FileInputStream(keyStoreClassPath.getFile());
			ks.load(readStream, "demo".toCharArray());
			readStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //
        
        
        SSLContext sslContext = SSLContexts
            .custom()
            .loadTrustMaterial(trustStoreclassPath.getFile(), "demo".toCharArray())
            .loadKeyMaterial(ks, "demo".toCharArray())
            .build();
        final CloseableHttpClient client = HttpClients
            .custom()
            .setSSLContext(sslContext)
            .build();
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
//		return new RestTemplate();
    }

	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl");
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

}
