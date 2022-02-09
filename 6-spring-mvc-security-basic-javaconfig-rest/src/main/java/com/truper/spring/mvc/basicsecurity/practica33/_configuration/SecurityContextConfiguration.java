package com.truper.spring.mvc.basicsecurity.practica33._configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.truper.spring.mvc.basicsecurity.practica33.userdetailsservice.CustomUserDetailsService;
import com.truper.spring.mvc.basicsecurity.practica33.userdetailsservice.UserDetailsDao;

@Configuration

// Habilita configuracion web con Spring Security
@EnableWebSecurity

// Extiende de WebSecurityConfigurerAdapter
public class SecurityContextConfiguration extends WebSecurityConfigurerAdapter {
	
	public static final String REALM_NAME = "Spring Security with Basic Authentication Application (Java config)";

	// Sobre escribe el metodo configure(AuthenticationManagerBuilder auth)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*auth.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("ADMIN").and()
				.withUser("xvanhalenx").password("123123").roles("ROOT","ADMIN").and()
				.withUser("user").password("user").roles("USER");*/
		
		auth.userDetailsService(customUserDetailsService());
	}

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService(userDetailsDao());
	}

	@Bean
	public UserDetailsDao userDetailsDao() {
		return new UserDetailsDao();
	}

	// Sobre escribe el metodo configure(HttpSecurity http)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/welcome").authenticated()
				.antMatchers("/").permitAll()
				.antMatchers("/root*/**").hasAuthority("ROLE_ROOT")
				.antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/user*/**").hasAuthority("ROLE_USER")
			.and()
				.httpBasic()
					.authenticationEntryPoint(authenticationEntryPoint())
					//.realmName(REALM_NAME)
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
					.accessDeniedHandler(customAccessDeniedHandler())
					
			;
	}
	
	// Sobre escribe el metodo configure(WebSecurity web)
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
		
		// GET /fun.api.com
		//			/fun.api.com/movies
		//			/fun.api.com/music
		//			/fun.api.com/photos
		
		// OPTIONS /fun.api.com/movies
		// 			GET, POST, ...
		
		// GET /fun.api.com/movies
	}

	
	// Define Bean AccessDeniedHandler
	@Bean
	public AccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	// Define Bean BasicAuthenticationEntryPoint
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint(REALM_NAME);
	}
	
}
