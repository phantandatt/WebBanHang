package com.tandat.webdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;

import com.tandat.webdemo.service.UserService;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	UserService userService;

	private final String[] PUBLIC_ENDPOINTS = { "/css/**", "/WEB-INF/**", "/registerPerfome", "/login" };

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.formLogin(form -> form.loginPage("/loginPerfome").defaultSuccessUrl("/home", true).permitAll())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll().anyRequest().authenticated())
				.csrf(CsrfAuthenticationStrategy -> CsrfAuthenticationStrategy.disable())
				.authenticationProvider(authenticationProvider()).build();
	}

	@Bean
	UserDetailsManager inMemoryUserDetailManageer() {
		// tên và mật khẩu
		var admin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		var user = User.withUsername("user").password("{noop}user").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
