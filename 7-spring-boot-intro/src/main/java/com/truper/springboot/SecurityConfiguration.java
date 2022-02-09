package com.truper.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetails;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails)
			//.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.passwordEncoder(new BCryptPasswordEncoder())
			;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/welcome").authenticated()
				.antMatchers("/api/**").authenticated()
				.antMatchers("/user").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/root").hasRole("ROOT")
			.and()
				.formLogin()
			.and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler)
				;
	}
	
}
