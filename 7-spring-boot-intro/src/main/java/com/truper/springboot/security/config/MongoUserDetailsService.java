package com.truper.springboot.security.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.truper.springboot.mongo.documents.UserEntity;
import com.truper.springboot.mongo.repositories.UserEntityRepository;

@Component
@Primary
public class MongoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserEntityRepository userEntityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userEntityRepository.findByUsername(username)
													.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles()
																					  .stream()
																					  .map(r -> new SimpleGrantedAuthority(r))
																					  .collect(Collectors.toList()));
	}

}
