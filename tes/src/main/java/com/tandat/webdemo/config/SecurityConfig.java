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

@Configuration // spring
//  Kích hoạt tính năng bảo mật web của Spring Security
@EnableWebSecurity
//  Kích hoạt tính năng bảo mật dựa trên phương thức của Spring Security
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	// Spring Security sử dụng UserService để xác thực người dùng
	UserService userService;

	// url user có thể access mà không cần login
	private final String[] PUBLIC_ENDPOINTS = { "/css/**", "/WEB-INF/**", "/registerPerfome", "/login" };

	@Bean
	// Phương thức này tạo và cấu hình một DaoAuthenticationProvider, là một
	// implementation của AuthenticationProvider của Spring Security
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		// setUserDetailsService(userService): Đặt userService làm UserDetailsService để
		// Spring Security có thể tìm kiếm và xác thực thông tin người dùng
		authProvider.setUserDetailsService(userService);
		// Đặt passwordEncoder() làm PasswordEncoder để mã hóa mật khẩu người dùng.
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	// SecurityFilterChain là một chuỗi các bộ lọc bảo mật của Spring Security
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Cấu hình đăng nhập dựa trên form. Nếu người dùng chưa đăng nhập và truy cập
		// vào một trang yêu cầu xác thực, họ sẽ được chuyển hướng đến /loginPerfome để
		// đăng nhập. Sau khi đăng nhập thành công, họ sẽ được chuyển hướng đến /home.
		// .permitAll() cho phép truy cập vào trang đăng nhập mà không cần xác thực
		return http.formLogin(form -> form.loginPage("/loginPerfome").defaultSuccessUrl("/home", true).permitAll())
				// Cấu hình các điều kiện xác thực cho các yêu cầu HTTP
				.authorizeHttpRequests(
						// cho phép truy cập vào các đường dẫn được khai báo trong PUBLIC_ENDPOINTS mà không cần xác thực. tất cả các yêu cầu khác đều phải được xác thực.
						auth -> auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll().anyRequest().authenticated())
				// Vô hiệu hóa CSRF (Cross-Site Request Forgery) protection để đơn giản hóa quá trình phát triển và kiểm thử
				.csrf(CsrfAuthenticationStrategy -> CsrfAuthenticationStrategy.disable())
				// Đặt authenticationProvider() đã được cấu hình làm AuthenticationProvider cho SecurityFilterChain
				.authenticationProvider(authenticationProvider()).build();
	}

	@Bean
	// lưu trữ thông tin người dùng trong bộ nhớ
	UserDetailsManager inMemoryUserDetailManageer() {
		//  hai người dùng mẫu: "admin" và "user", mỗi người dùng có mật khẩu được mã hóa bằng {noop} (không mã hóa) để đơn giản. Các người dùng này có vai trò là "ADMIN" và "USER"
		var admin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		var user = User.withUsername("user").password("{noop}user").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	// mã hóa mật khẩu
	@Bean
	PasswordEncoder passwordEncoder() {
		// Phương thức này tạo ra một BCryptPasswordEncoder với độ mạnh là 10 để mã hóa mật khẩu người dùng
		// BCryptPasswordEncoder là một trong những mã hóa mật khẩu mạnh và an toàn được khuyến khích sử dụng bởi Spring Security
		return new BCryptPasswordEncoder(10);
	}

}
