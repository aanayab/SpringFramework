package com.truper.spring.mvc.digestsecurity.practica34._configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import com.truper.spring.mvc.digestsecurity.practica34.repository.UserDetailsRepository;
import com.truper.spring.mvc.digestsecurity.practica34.service.CustomUserDetailsService;

@Configuration

// Habilita configuracion web con Spring Security
@EnableWebSecurity

// Extiende de WebSecurityConfigurerAdapter
public class SecurityContextConfiguration extends WebSecurityConfigurerAdapter {

	public static final String REALM_NAME = "Spring Security with Digest Authentication App (java config)";

	// Inyecta
	@Autowired
	private UserDetailsRepository hardcodedUserDetailsRepository;
	
	// Sobre escribe el metodo configure(AuthenticationManagerBuilder auth)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService());
	}

	// Sobre escribe el metodo configure(HttpSecurity http)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/welcome").authenticated()
				.antMatchers("/").permitAll()
				.antMatchers("/root*/**").hasAuthority("ROLE_ROOT")// .hasRole("ROOT")
				.antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")// .hasRole("ADMIN")
				.antMatchers("/user*/**").hasAuthority("ROLE_USER")// .hasRole("USER")
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
					.accessDeniedHandler(customAccessDeniedHandler())
					.authenticationEntryPoint(customDigestAuthenticationEntryPoint())
			.and()
				.addFilterAfter(digestAuthenticationFilter(), BasicAuthenticationFilter.class)
		;
	}
	
	// Define Bean UserDetailsService
	@Bean
	@DependsOn("hardcodedUserDetailsRepository")
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService(hardcodedUserDetailsRepository);
	}
	
	// Define Bean CustomDigestAuthenticationEntryPoint
	@Bean
	public DigestAuthenticationEntryPoint customDigestAuthenticationEntryPoint() {
		return new CustomDigestAuthenticationEntryPoint("secret-key", REALM_NAME, 10);
	}
	
	// Define Bean DigestAuthenticationFilter
	@Bean
	public Filter digestAuthenticationFilter() {
		DigestAuthenticationFilter daf = new DigestAuthenticationFilter();
		daf.setAuthenticationEntryPoint(customDigestAuthenticationEntryPoint());
		daf.setUserDetailsService(customUserDetailsService());
		return daf;
	}
	
	// Define Bean AccessDeniedHandler
	@Bean
	public AccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// Sobre escribe el metodo configure(WebSecurity web)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
