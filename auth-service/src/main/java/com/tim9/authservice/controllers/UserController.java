package com.tim9.authservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private HttpServletRequest context;
	
//	@Autowired
//	private RestTemplate rest;
	
//	@GetMapping("/marko")
//	public String getUser() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		String authorization = context.getHeader("Authorization");
//		String token = "";
//		if (authorization != null && authorization.toLowerCase().startsWith("bearer")) {
//			token = authorization.substring("Bearer".length()).trim();
//			headers.add("Authorization", "Bearer " + token);
//		}
//		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//		ResponseEntity<String> response = rest.exchange("https://auth-service/user/all", HttpMethod.GET, entity, String.class);
//		return "Unutar /marko i : " + response.getBody();
//	}
}
