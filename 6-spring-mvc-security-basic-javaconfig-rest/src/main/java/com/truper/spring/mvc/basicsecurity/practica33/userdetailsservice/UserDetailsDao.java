package com.truper.spring.mvc.basicsecurity.practica33.userdetailsservice;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// De esta clase... que tambien tiene que ser un bean... sacas el usuario de donde quieras...
public class UserDetailsDao {

	public UserDetails fetchUserByUsername(String username) {
		switch (username) {

		case "admin":
			return new User("admin", "admin123", createAuthorities("ADMIN"));

		case "xvanhalenx":
			return new User("xvanhalenx", "123123", createAuthorities("ROOT", "ADMIN"));

		case "user":
			return new User("user", "user", createAuthorities("USER"));

		default:
			break;
		}

		throw new UsernameNotFoundException("User " + username + " not found");
	}

	private Collection<? extends GrantedAuthority> createAuthorities(String... roles) {
		return Arrays.asList(roles).stream()
				.map(role -> "ROLE_" + role)
				.map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());
	}

}
