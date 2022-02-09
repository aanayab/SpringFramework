package com.truper.spring.mvc.jwtsecurity.practica35._configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityContextConfiguration extends WebSecurityConfigurerAdapter {

	public static final String REALM_NAME = "Spring Security with JWT Authentication App (java config)";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
					.withUser("admin").password("admin").roles("ADMIN")
					.and()
					.withUser("xvanhalenx").password("123123").roles("ROOT", "ADMIN")
					.and()
					.withUser("user").password("user").roles("USER");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
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
            		.authenticationEntryPoint(authenticationEntryPoint())
            		.accessDeniedHandler(accessDeniedHandler())
            .and()
            	.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            	.addFilterBefore(jwtAuthroizationFilter(), 	UsernamePasswordAuthenticationFilter.class)
            ;
    }

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new JwtAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint(REALM_NAME);
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		return new JwtAuthenticationFilter("/api/authenticate", authenticationManager());
	}
	
	@Bean
	public JwtAuthroizationFilter jwtAuthroizationFilter() throws Exception {
		return new JwtAuthroizationFilter();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
}
