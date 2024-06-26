package com.tandat.webdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tandat.webdemo.model.Account;
import com.tandat.webdemo.model.Discount;
import com.tandat.webdemo.model.Product;
import com.tandat.webdemo.service.AccountService;
import com.tandat.webdemo.service.CategoryService;
import com.tandat.webdemo.service.DiscountService;
import com.tandat.webdemo.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class AdController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping
	public String admin(ModelMap model, @ModelAttribute("discount") Discount discount) {
		long totalAccount = accountService.totalAccount();
		List<Account> list = accountService.getAll();
		List<Discount> listDiscount = discountService.getAllDiscount();
		List<Product> ListProduct = productService.getAllProd();

		model.addAttribute("totalProduct", productService.totalProduct());
		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListDiscount", listDiscount);
		model.addAttribute("ListAccount", list);
		model.addAttribute("TotalAccount", totalAccount);
		return "admin";
	}

	@GetMapping("/all")
	@ResponseBody
	public List<Discount> getAllDiscounts() {
		return discountService.getAllDiscounts();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Discount getDiscountById(@PathVariable String id) {
		return discountService.getDiscountById(id).orElse(null);
	}

	@PostMapping
	@ResponseBody
	public Discount createDiscount(@RequestBody Discount discount) {
		return discountService.saveDiscount(discount);
	}

	@GetMapping(value = "them")
	public String createDiscount(@RequestParam("code") String code, @RequestParam("ValueOfCode") int value) {
		discountService.saveDiscount(code, value);
		return "redirect:/admin";
	}

	@GetMapping("/delete")
	public String deleteDiscount(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
		discountService.deleteDiscount(code);
		return "redirect:/admin";
	}

	@GetMapping("/xoataikhoan")
	public String deleteAccount(@RequestParam String username, RedirectAttributes redirectAttributes) {
		accountService.deleteAccount(username);
		return "redirect:/admin";
	}

	@GetMapping("/setrole")
	public String setRole(@RequestParam String username, RedirectAttributes redirectAttributes) {
		Account acc = accountService.getAcc(username);
		switch (acc.getRole()) {
		case 1:
			acc.setRole(0);
			break;
		case 0:
			acc.setRole(1);
			break;
		default:
			break;
		}
		accountService.setRole(username, acc.getRole());
		return "redirect:/admin";
	}

	@GetMapping("/suamagiamgia")
	public String editValueDiscount(@RequestParam("code") String code, @RequestParam("ValueOfCode") int valueOfCode) {
		discountService.updateValue(code, valueOfCode);
		return "redirect:/admin";
	}
	@GetMapping("/xoasanpham")
	public String deleteProduct(@RequestParam("ProductID") int idProduct) {
		productService.deleteById(idProduct);
		return "redirect:/admin";
	}
}
