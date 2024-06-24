package com.tandat.webdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import com.tandat.webdemo.model.Account;
import com.tandat.webdemo.model.Cart;
import com.tandat.webdemo.model.Product;
import com.tandat.webdemo.service.CartService;
import com.tandat.webdemo.service.ProductService;

@Controller
public class CartController  {

	@Autowired
	private CartService cartService;
	
	@Autowired 
	private ProductService service;
	
//	@GetMapping("/cart")
//	public @ResponseBody List<Cart> getCartsByUserName(@RequestParam String username){
//		System.out.println(username);
//		return cartService.getCartByUsername(username);
//	}
	
	@GetMapping("/cart")
	public ModelAndView getCartByUserName(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("cart");
		Account account = (Account) session.getAttribute("acc");
		 String username = account.getUsername();
		List<Cart> liCarts = cartService.getCartByUsername(username);
		modelAndView.addObject("ListCart", liCarts);
		return modelAndView;
	}
	
	@PostMapping("/cart")
	public ModelAndView addtoCart(@RequestParam int ProductID, @RequestParam int soluong, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("cart");
		Product product = service.findById(ProductID);
		Account account = (Account) session.getAttribute("acc");
		Cart newCart = new Cart(account.getUsername(), ProductID, product.getProductName(), soluong, product.getPrice(), product.getImgPath());
		cartService.save(newCart);
		List<Cart> liCarts = cartService.getCartByUsername(account.getUsername());
		modelAndView.addObject("ListCart", liCarts);
		return modelAndView;

		
	}
}
