package com.tandat.webdemo;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfig {
	@Bean
	@SessionScope
	public HttpSession httpSession() {
		return new CustomHttpSession();
	}
}
