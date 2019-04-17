package com.tim9.accommodationservice.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tim9.accommodationservice.utils.JwtTokenAuthenticationFilter;

@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()
		     // make sure we use stateless session; session won't be used to store user's state.
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            // handle an authorized attempts 
	            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
		    // Add a filter to validate user credentials and add token in the response header
			
		    // What's the authenticationManager()? 
		    // An object provided by WebSecurityConfigurerAdapter, used to authenticate the user passing user's credentials
		    // The filter needs this auth manager to authenticate the user.	
		    .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		    .authorizeRequests()
		    .antMatchers(HttpMethod.GET, "/accommodations").permitAll()
		    .antMatchers(HttpMethod.GET, "/accommodations/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/accommodations/agent/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/reservations/1").hasAuthority("ROLE_ADMIN")
//		    .antMatchers(HttpMethod.GET, "/accommodations/**").permitAll()
		    // any other requests must be authenticated
		    .anyRequest().authenticated();
	}
	
	@Bean
	public JwtConfig jwtConfig() {
        	return new JwtConfig();
	}
}