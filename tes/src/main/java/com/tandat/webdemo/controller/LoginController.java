package com.tandat.webdemo.controller;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.tandat.webdemo.entity.User;
import com.tandat.webdemo.form.RegisterForm;
import com.tandat.webdemo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
	String register(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "registerPerfome";
	}

	@PostMapping("/registerPerfome")
	public String registerUser(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult,
			Model model) {

		// Custom validation for matching passwords
		if (!registerForm.isPasswordsMatch()) {
			bindingResult.rejectValue("againPassword", "error.userForm", "Mật khẩu nhập lại không khớp");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("registerForm", registerForm);
			return "registerPerfome";
		}
		User user = new User();
		user.setUsername(registerForm.getUsername());
		user.setPassword(registerForm.getPassword());
		user.setAddress(registerForm.getAddress());
		user.setDob(registerForm.getDob());
		user.setEmail(registerForm.getEmail());
		if (userService.registerUser(user)) {
		return "loginPerfome";
		}
		else {
			bindingResult.rejectValue("username", "error.username", "Tên người dùng đã tồn tại");
			model.addAttribute("registerForm", registerForm);
			return "registerPerfome";
		}
	

	}

//	@PostMapping("/registerPerfome")
//	ModelAndView registerPost(@RequestParam("username") String username, @RequestParam("password") String password,
//			RedirectAttributes redirectAttributes) {
//		// Đăng ký user
//		if (userService.registerUser(username, password)) {
//			return new ModelAndView("/loginPerfome");
//		}
//		ModelAndView model =new ModelAndView();
//		model.addObject("error","Đăng ký thất bại. Vui lòng thử lại.");
//		return model;
//	}

	@GetMapping("/logoutPerfome")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "loginPerfome"; // Redirect to login page with a logout parameter
	}

}