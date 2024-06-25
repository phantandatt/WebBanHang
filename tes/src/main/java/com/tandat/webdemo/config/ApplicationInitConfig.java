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

// cấu hình các bean và các thành phần khác của ứng dụng
@Configuration
// tự động tạo các trường (fields) của class và cấu hình level của các fields là private, đồng thời đánh dấu các fields là final (không thể thay đổi giá trị sau khi khởi tạo)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// tạo ra một constructor chứa các tham số dành cho các fields đã đánh dấu là final
@RequiredArgsConstructor
public class ApplicationInitConfig {
	@Autowired
	// PasswordEncoder được Spring Security cung cấp để mã hóa và giải mã mật khẩu
	PasswordEncoder passwordEncoder;

	@Bean
	// ApplicationRunner là một interface của Spring Boot, cho phép chạy logic sau
	// khi ứng dụng đã được khởi động
	// UserRepository như là một tham số
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			// Kiểm tra xem có tồn tại user có username là "testing" trong cơ sở dữ liệu hay
			// không
			if (userRepository.findByUsername("testing").isEmpty()) {
				// Nếu không tồn tại, tạo một đối tượng User, đặt username là "testing" và mã
				// hóa mật khẩu "testing" bằng passwordEncoder
				User user = new User();
				user.setUsername("testing");
				user.setPassword(passwordEncoder.encode("testing"));
				// Lưu user vào cơ sở dữ liệu thông qua userRepository.save(user)
				userRepository.save(user);
				// In ra thông tin của user đã lưu
				System.out.println(user);
			}
		};
	}
}
