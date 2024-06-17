package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.Cart;

import service.CartService;

@Controller
public class CartController  {

	@Autowired
	private CartService cartService;
	
//	@GetMapping("/cart")
//	public @ResponseBody List<Cart> getCartsByUserName(@RequestParam String username){
//		System.out.println(username);
//		return cartService.getCartByUsername(username);
//	}
	
	@GetMapping("/cart")
	public ModelAndView getCartByUserName(@RequestParam String username) {
		ModelAndView modelAndView = new ModelAndView("cart");
		List<Cart> liCarts = cartService.getCartByUsername(username);
		modelAndView.addObject("ListCart", liCarts);
		return modelAndView;
	}
}
