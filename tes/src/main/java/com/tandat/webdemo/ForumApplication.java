package com.tandat.webdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@ComponentScan(basePackages = "com.tandat.Forum,controller,model")
//@ComponentScan({ "com.tandat.Forum", "controller", "model", "service", "respository","SecurityConfig","handler" })
//@EntityScan(basePackages =  "model")
//@EnableJpaRepositories("respository")

public class ForumApplication extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ForumApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
