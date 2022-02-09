package com.truper.spring.mvc.digestsecurity.practica34.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.truper.spring.mvc.digestsecurity.practica34.repository.UserDetailsRepository;

// Analiza clase CustomUserDetailsService
public class CustomUserDetailsService implements UserDetailsService {

	private UserDetailsRepository userDetailsRepository;

	public CustomUserDetailsService(UserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetailsRepository.findByUsername(username);
	}

}
