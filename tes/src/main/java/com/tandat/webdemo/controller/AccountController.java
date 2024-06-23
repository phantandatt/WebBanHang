package com.tandat.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/homee")
public class AccountController {
	
	@GetMapping
	public String home() {
		return "index";
	}
	

}
