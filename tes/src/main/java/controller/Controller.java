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
import model.Category;
import model.Discount;
import model.Product;
import service.AccountService;
import service.CategoryService;
import service.DiscountService;
import service.ProductService;

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

	@GetMapping("/home")
	public String getHomePage(Model model) {
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();

		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
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

	@GetMapping("/shop")
	public String shop(Model model) {
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();

		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
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
