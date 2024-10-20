package com.mayo.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity (consider enabling it in production)
                .authorizeRequests()
                .antMatchers("/api/public/**", "/version").permitAll() // Allow public access to these endpoints
                .anyRequest().authenticated() // Secure all other endpoints
                .and()
                .httpBasic(); // Enable basic authentication
    }
}

