package com.tandat.webdemo.controller;

import org.springframework.security.core.Authentication;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.tandat.webdemo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/loginPerfome")
	String login() {
		return "login";
	}

	@PostMapping("/loginPerfome")
	public String loginPost() {
		// Cách lấy user đang đăng nhập (tự set vào sử dụng)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.print(currentPrincipalName);
		///
		return "home";
	}

	@GetMapping("/registerPerfome")
	String register() {
		return "registerPerfome";
	}

	@PostMapping("/registerPerfome")
	ModelAndView registerPost(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes) {
		if (userService.registerUser(username, password)) {
			return new ModelAndView("/loginPerfome");
		}
		ModelAndView model =new ModelAndView();
		model.addObject("error","Đăng ký thất bại. Vui lòng thử lại.");
		return model;
	}
	
	@PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "loginPerfome"; // Redirect to login page with a logout parameter
    }

}