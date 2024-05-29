package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private AccountService accountService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	private Account account;

	@GetMapping("/home")
	public String getHomePage(Model model) {
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();
		

		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();

		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
		return "shop";
	}

	@RequestMapping("/login")
	public String login(Account account) {

		return "login";
	}

	@PostMapping(value = "check")
	public String loginn(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes, HttpSession session) {
		boolean check = false;
		check = accountService.validateAccount(username, password);

		if (!check) {
			return "redirect:/login";
		} else

			account = accountService.getAcc(username);
		session.setAttribute("acc", account);
		return "redirect:/home";
	}

	@GetMapping("/register")
	public String register() {
		JSONArray list = new JSONArray();
		return "register";
	}

	@GetMapping("/ajax")
	public String ajax(@ModelAttribute("discount") Discount discount) {
		return "ajax";
	}
}
