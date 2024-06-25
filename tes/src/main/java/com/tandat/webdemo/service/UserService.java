package com.tandat.webdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tandat.webdemo.entity.CustomUserDetails;
import com.tandat.webdemo.entity.User;
import com.tandat.webdemo.respository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// được quản lý và tự động quản lý vòng đời bởi Spring Container
@Service
// để tạo constructor với các tham số final
@RequiredArgsConstructor
// để thiết lập access level và final cho tất cả các field.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

// Spring Security để tải thông tin người dùng từ cơ sở dữ liệu khi người dùng đăng nhập
	@Override
	public UserDetails loadUserByUsername(String username) {
		// Sử dụng UserRepository để tìm người dùng dựa trên username
		User user = userRepository.findByUsername(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException(username); // Nếu không tìm thấy người dùng, ném ra
															// UsernameNotFoundException
		}
		return new CustomUserDetails(user); // Trả về một đối tượng CustomUserDetails từ User cho Spring Security sử
											// dụng để xác thực
	}

	public boolean registerUser(User user) {
		// đăng ký user mới vào db
		// Kiểm tra xem username đã tồn tại hay chưa , Nếu username đã tồn tại, trả về
		// false
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			return false;
		}
		// Nếu chưa tồn tại, mã hóa mật khẩu sử dụng BCryptPasswordEncoder với mức độ mã
		// hóa là 10
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user); // Lưu người dùng mới vào cơ sở dữ liệu

		return true;
	}

	// kiểm tra user tồn tại không
	public User getUser(String username) {
		// Kiểm tra xem user có tồn tại trong database không?
		User user = userRepository.findByUsername(username).get();
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}
