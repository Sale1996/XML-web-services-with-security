package com.tim9.authservice.config;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

	// Spring doesn't inject/autowire to "static" fields. 
	// Link: https://stackoverflow.com/a/6897406
	@Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:perisicevbrk}")
    private String secret;
    
	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "JwtConfig [Uri=" + Uri + ", header=" + header + ", prefix=" + prefix + ", expiration=" + expiration
				+ ", secret=" + secret + "]";
	}
    
}