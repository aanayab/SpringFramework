package com.truper.spring.mvc.digestsecurity.practica34._configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.truper.spring.mvc.digestsecurity.practica34.repository.UserDetailsRepository;
import com.truper.spring.mvc.digestsecurity.practica34.repository.impl.HardcodedUserDetailsRepository;

@Configuration // Esta clase de configuracion sirve para definir beans del
				// RootApplicationContext
public class RootContextConfiguration {

	// Define bean HardcodedUserDetailsRepository
	@Bean
	public UserDetailsRepository hardcodedUserDetailsRepository() {
		return new HardcodedUserDetailsRepository();
	}
}