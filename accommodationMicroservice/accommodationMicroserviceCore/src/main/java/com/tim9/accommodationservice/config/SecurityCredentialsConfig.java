package com.tim9.accommodationservice.config;

//@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityCredentialsConfig { // extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private JwtConfig jwtConfig;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		    .csrf().disable()
//		     // make sure we use stateless session; session won't be used to store user's state.
//	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        .and()
//	            // handle an authorized attempts 
//	            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//	        .and()
//		    // Add a filter to validate user credentials and add token in the response header
//			
//		    // What's the authenticationManager()? 
//		    // An object provided by WebSecurityConfigurerAdapter, used to authenticate the user passing user's credentials
//		    // The filter needs this auth manager to authenticate the user.	
//		    .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//		    .authorizeRequests()
//		    .antMatchers(HttpMethod.GET, "/accommodations").permitAll()
//		    .antMatchers(HttpMethod.GET, "/accommodations/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/**").permitAll()
//		    .antMatchers(HttpMethod.POST, "/**").permitAll()
//		    .antMatchers(HttpMethod.PUT, "/**").permitAll()
//		    .antMatchers(HttpMethod.DELETE, "/**").permitAll()
//
////		    .antMatchers(HttpMethod.GET, "/accommodations/agent/**").permitAll()
////		    .antMatchers(HttpMethod.GET, "/reservations/1").hasAuthority("ROLE_ADMIN")
////		    .antMatchers(HttpMethod.GET, "/accommodations/**").permitAll()
//		    // any other requests must be authenticated
//		    .anyRequest().authenticated();
//		
//		http.cors();
//	}
//	
//	@Bean
//	public JwtConfig jwtConfig() {
//        	return new JwtConfig();
//	}
}