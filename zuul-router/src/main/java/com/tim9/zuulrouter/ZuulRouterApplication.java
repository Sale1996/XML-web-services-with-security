package com.tim9.zuulrouter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulRouterApplication {

//	@Bean
//	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
//		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
//		System.setProperty("javax.net.ssl.keyStore", "src/main/java/keyStore.jks");
//		System.setProperty("javax.net.ssl.keyStorePassword", "demo");
//		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/trustStore.jks");
//		System.setProperty("javax.net.ssl.trustStorePassword", "demo");
//		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
//		builder.withClientName("zuul");
//		builder.withSystemSSLConfiguration();
//		builder.withMaxTotalConnections(10);
//		builder.withMaxConnectionsPerHost(10);
//		args.setEurekaJerseyClient(builder.build());
//		return args;
//	}
	
//	@Bean
//    public RestTemplate restTemplate() throws Exception {
//        ClassPathResource trustStoreclassPath = new ClassPathResource("trustStore.jks");
//        ClassPathResource keyStoreClassPath = new ClassPathResource("keyStore.jks");
//        
//        //
//        KeyStore ks = null;
//		try {
//			ks = KeyStore.getInstance("JKS");
//			InputStream readStream = new FileInputStream(keyStoreClassPath.getFile());
//			ks.load(readStream, "demo".toCharArray());
//			readStream.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        //
//        
//        
//        SSLContext sslContext = SSLContexts
//            .custom()
//            .loadTrustMaterial(trustStoreclassPath.getFile(), "demo".toCharArray())
//            .loadKeyMaterial(ks, "demo".toCharArray())
//            .build();
//        final CloseableHttpClient client = HttpClients
//            .custom()
//            .setSSLContext(sslContext)
//            .build();
//        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
////		return new RestTemplate();
//    }
	
	public static void main(String[] args) {
//		System.setProperty("javax.net.debug", "ssl");
		SpringApplication.run(ZuulRouterApplication.class, args);
	}

}
