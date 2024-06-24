package com.tandat.webdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tandat.webdemo.entity.User;
import com.tandat.webdemo.respository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    
    // hàm chạy ban đầu khi khởi chạy chương trình để tạo ra user mẫu testing
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
        	// Khi chương trình chạy
            // Insert vào csdl một user.
        	if(userRepository.findByUsername("testing").isEmpty()) {
            User user = new User();
            user.setUsername("testing");
            user.setPassword(passwordEncoder.encode("testing"));
            userRepository.save(user);
            System.out.println(user);
        	}
        };
    }
}
