package com.tandat.webdemo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tandat.webdemo.model.Account;
import com.tandat.webdemo.model.Category;
import com.tandat.webdemo.model.Discount;
import com.tandat.webdemo.model.Product;
import com.tandat.webdemo.service.AccountService;
import com.tandat.webdemo.service.CategoryService;
import com.tandat.webdemo.service.DiscountService;
import com.tandat.webdemo.service.ProductService;
import com.tandat.webdemo.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Controller

public class Controller {
	@Autowired
	private AccountService service;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String getHomePage(Model model,HttpSession session) {
		// Cách lấy user đang đăng nhập (tự set vào sử dụng)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Account account = service.getAcc(currentPrincipalName);
		session.setAttribute("acc", account);
		System.out.print(currentPrincipalName);
		///
		System.out.print(false);
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();

		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		if (userService.getUser(currentPrincipalName).getRole() == 1) {
			long totalAccount = service.totalAccount();
			List<Account> list = service.getAll();
			List<Discount> listDiscount = discountService.getAllDiscount();
			List<Product> ListProduct = productService.getAllProd();

			model.addAttribute("totalProduct", productService.totalProduct());
			model.addAttribute("ListProduct", ListProduct);
			model.addAttribute("ListDiscount", listDiscount);
			model.addAttribute("ListAccount", list);
			model.addAttribute("TotalAccount", totalAccount);
			return "admin";
		}
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model, @RequestParam(defaultValue = "0") int page) {
		List<Category> ListCategory = categoryService.findAll();

		Page<Product> ListProduct = productService.getAllProd(page);

		model.addAttribute("ListProduct", ListProduct.getContent());
		model.addAttribute("totalPage", ListProduct.getTotalPages());
		model.addAttribute("pageNo", page);
		model.addAttribute("ListCategory", ListCategory);

		return "shop";
	}

	@GetMapping("/shop/{page}")
	public @ResponseBody List<Product> shop1(Model model, @RequestParam int page) {

		List<Category> ListCategory = categoryService.findAll();
		Page<Product> ListProduct = productService.getAllProd(page);
		List<Product> response = ListProduct.getContent();

		return response;
	}

	@GetMapping("/shop/page")
	public ModelAndView shopPage(@RequestParam(defaultValue = "0") int page) {
		int pageSize = 10;
		Page<Product> productsPage = productService.getAllProd(page);
		List<Category> ListCategory = categoryService.findAll();
		ModelAndView modelAndView = new ModelAndView("productList");
		modelAndView.addObject("ListProduct", productsPage.getContent());
		modelAndView.addObject("pageNo", page);
		modelAndView.addObject("totalPage", productsPage.getTotalPages());
		modelAndView.addObject("ListCategory", ListCategory);
		return modelAndView;
	}

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";
	}

	@GetMapping("/error/403")
	public String error() {
		return "403";
	}

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "s";
	}
	
	@GetMapping("/search")
	public ModelAndView searchProducts(@RequestParam("search") String keyword) {
		ModelAndView view = new ModelAndView("shop");
		List<Product> ListProduct = productService.searchProducts(keyword);
		List<Category> ListCategory = categoryService.findAll();

		view.addObject("ListCategory", ListCategory);
		view.addObject("ListProduct", ListProduct);
		productService.searchProducts(keyword);

		return view;
	}

}
