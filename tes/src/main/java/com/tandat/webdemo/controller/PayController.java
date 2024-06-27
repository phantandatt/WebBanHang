package com.tandat.webdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tandat.webdemo.model.Account;
import com.tandat.webdemo.model.Cart;
import com.tandat.webdemo.service.CartService;
import com.tandat.webdemo.service.DiscountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {

	@Autowired
	CartService cartService;
	@Autowired
	DiscountService discountService;

	@GetMapping("/checkout")
	public String checkoutpage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Cart> carts = cartService.getCartByUsername(username);
		double TotalPrice = 0;
		int i = 0;
		for (Cart cart : carts) {
			TotalPrice += cart.getPrice() * cart.getAmount();
			i++;
		}
		System.out.println(i);
		model.addAttribute("ListCart", carts);
		model.addAttribute("TotalPrice", TotalPrice);

		return "checkout";
	}

	@GetMapping("/applyDiscount")
	public String applyDiscount(Model model, @RequestParam("code") String code) {
		int valueOfCode = 0;
		if (discountService.checkDiscount(code)) {
			valueOfCode = discountService.value(code);
		}
		model.addAttribute("ValueOfCode", valueOfCode);
		model.addAttribute(code, code);
		return "/checkout";
	}

}