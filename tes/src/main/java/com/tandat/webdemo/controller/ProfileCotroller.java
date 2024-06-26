package com.tandat.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tandat.webdemo.model.Account;
import com.tandat.webdemo.service.AccountService;



@Controller
@RequestMapping(value = "/profile")
public class ProfileCotroller {
	@Autowired
	private AccountService accountService;

	@GetMapping
	public String getProfilePage(Model model) {
		return "profile";
	}

	@PostMapping(value = "/changepassword")
	public String changPassword(Model model,
			@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("againPassword") String againPassword) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Account acc = accountService.getAcc(username);
		
		boolean status = false;
		String mess = "";
		System.out.println("Mật khẩu hiện tại : "+ currentPassword);
		System.out.println("Mật khẩu trong database: " +acc.getPassword());
		if (equalPassword(currentPassword, acc.getPassword())) {
			if (newPassword.equals(againPassword)) {
				BCryptPasswordEncoder encode = new BCryptPasswordEncoder(10);
				String newPassword2 = encode.encode(newPassword);
				acc.setPassword(newPassword2);
				accountService.updateAccount(username, newPassword2);
				status = true;
				mess = "thanh cong";
				System.out.println("Thanh Cong");
			} else {
				mess = "nhap lai mat khau khong dung";
				System.out.println(mess);
			}
		} else {
			mess = "mat khau hien tai khong dung";
			System.out.println(mess);
		}
		model.addAttribute("status", status);
		model.addAttribute("mess", mess);
	
		return "redirect:/profile";
	}
	public boolean equalPassword(String currentPassword, String password) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder(10);
		return encode.matches(currentPassword, password);
	}
}