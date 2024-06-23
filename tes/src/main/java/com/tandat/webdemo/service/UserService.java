package com.tandat.webdemo.service;

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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {

     UserRepository userRepository;

     
     // kiểm tra user tồn tại không
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public boolean registerUser(String userName,String passwrod) {
    	// đăng ký user
    	if(userRepository.findByUsername(userName).isPresent()) {
    		return false;
    	}
    	// mã hóa mật khẩu
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    	User user = User.builder().username(userName).password(passwordEncoder.encode(passwrod)).build();
    	userRepository.save(user);
    	
    	return true;
    }

}

