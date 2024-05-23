package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Account;
import model.Discount;
import service.AccountService;
import service.DiscountService;

@org.springframework.stereotype.Controller

public class Controller {
	@Autowired
	private AccountService service;
	@Autowired
	private DiscountService discountService;

	@GetMapping("/home")
	public String getHomePage() {
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		long totalAccount = service.totalAccount();
		List<Account> list = service.getAll();
		List<Discount> listDiscount = discountService.getAllDiscount();
		model.addAttribute("ListDiscount", listDiscount);
		model.addAttribute("ListAccount", list);
		model.addAttribute("TotalAccount", totalAccount);
		return "admin";
	}

	@GetMapping("/shop")
	public String shop() {
		return "shop";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		JSONArray list = new JSONArray();
		return "register";
	}

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";
	}
}
