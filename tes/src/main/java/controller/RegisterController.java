package controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import model.Account;
import model.UseFormRegister;
import service.AccountService;

@Controller
@RequestMapping(value = { "/register" })
public class RegisterController {
	private String Url = "/register";
	@Autowired
	 AccountService accountService;
	
	 @GetMapping(value = {"","/"})
	  public String loginPage(Model model) {
	        Account accountDTO = new Account();
	        model.addAttribute("accountDTO", accountDTO);
	        model.addAttribute("error", null);
	        UseFormRegister useFormRegister = new UseFormRegister();
	        model.addAttribute("useForm", useFormRegister);
	        System.out.println("register");
	        model.addAttribute("mess", "hello");
	        return "register";
	    }
	  
	  @PostMapping(value = {"","/"})
	  public  Map<String, Object> submitForm(@Valid @ModelAttribute("useForm") UseFormRegister useFormRegister, BindingResult bindingResult) {
	        Map<String, Object> response = new HashMap<>();
	        System.out.println("1");
	        if (accountService.emailUser(useFormRegister.getUsername())) {
	            bindingResult.rejectValue("username", "error.username", "Tài khoản đã tồn tại");
	        }
	        if (accountService.emailExists(useFormRegister.getEmail())) {
	            bindingResult.rejectValue("email", "error.email", "Email đã được sử dụng");
	        }
	        if(!useFormRegister.getPassword().equals(useFormRegister.getAgainPassword())) {
	        	bindingResult.rejectValue("againPassword", "error.againPassword", "Xác nhận mật khẩu không tương ứng");
	        }
	        if (bindingResult.hasErrors()) {
	            bindingResult.getFieldErrors().forEach(fieldError -> 
	                response.put(fieldError.getField(), fieldError.getDefaultMessage()));
	            response.put("status", "error");
	            
	           
	        } else {
	            // Save account to the database if no errors
	        	String username = useFormRegister.getUsername();
	        	String password = useFormRegister.getPassword();
	        	Date dob = useFormRegister.getDob();
	        	String email = useFormRegister.getEmail();
	        	String address = useFormRegister.getAddress();
	        	Account account = new Account(username, password, dob.toString(), email, address, 0);
	        	accountService.save(account);
	            response.put("status", "success");
	            response.put("message", "User registered successfully");
//	            response.put("email1", account.getEmail());
	        }
	        System.out.println(response);
	        return response;
	    }
	
}
