package com.tim9.reservationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private Environment environment;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // other security configuration missing

//        http.portMapper()
//                .http(Integer.parseInt(environment.getProperty("server.http.port"))) // http port defined in yml config file
//                .mapsTo(Integer.parseInt(environment.getProperty("server.port"))); // https port defined in yml config file

        // we only need https on /auth
//        http.requiresChannel()
//                .antMatchers("/auth/**").requiresSecure()
//                .anyRequest().requiresSecure();
    }
}