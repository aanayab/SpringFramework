package com.truper.spring.mvc.basicsecurity.practica33.userdetailsservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	private final UserDetailsDao userDetailsDao;

	public CustomUserDetailsService(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userDetailsDao.fetchUserByUsername(username);
	}

}
