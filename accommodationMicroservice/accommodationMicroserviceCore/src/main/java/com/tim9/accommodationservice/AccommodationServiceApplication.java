package com.tim9.accommodationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tim9.accommodationserviceclient.feignClients", "com.tim9.userserviceClient.feignClients", "com.tim9.reservationserviceClient.feignClients"})
public class AccommodationServiceApplication {
	
//	@Bean
//	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
//		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
//		System.setProperty("javax.net.ssl.keyStore", "src/main/java/keyStore.jks");
//		System.setProperty("javax.net.ssl.keyStorePassword", "demo");
//		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/trustStore.jks");
//		System.setProperty("javax.net.ssl.trustStorePassword", "demo");
//		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
//		builder.withClientName("accommodation");
//		builder.withSystemSSLConfiguration();
//		builder.withMaxTotalConnections(10);
//		builder.withMaxConnectionsPerHost(10);
//		args.setEurekaJerseyClient(builder.build());
//		return args;
//	}
//
//	@Bean
//	@LoadBalanced
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
//    }
	
	public static void main(String[] args) {
//		System.setProperty("javax.net.debug", "ssl");
		SpringApplication.run(AccommodationServiceApplication.class, args);
	}

}
