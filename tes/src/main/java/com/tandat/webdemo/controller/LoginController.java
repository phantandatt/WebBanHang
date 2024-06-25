package com.tandat.webdemo.controller;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.tandat.webdemo.entity.User;
import com.tandat.webdemo.form.RegisterForm;
import com.tandat.webdemo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
// xử lý các yêu cầu từ người dùng.
@Controller
class LoginController {
	//  để có thể sử dụng các phương thức của service.
	@Autowired
	private UserService userService;
	
// Phương thức này xử lý yêu cầu GET đến "/loginPerfome"
	@GetMapping("/loginPerfome")
	// Nó trả về chuỗi "login" là tên của view (template) được hiển thị cho người dùng khi truy cập vào trang đăng nhập
	String login() {
		return "login";
	}
// Phương thức này xử lý yêu cầu POST đến "/loginPerfome".
	@PostMapping("/loginPerfome")
	public String loginPost() {
		// sử dụng SecurityContextHolder để lấy thông tin về người dùng hiện tại đã đăng nhập (Authentication). getName() trả về tên người dùng đăng nhập.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		// in ra tên người dùng đã đăng nhập và sau đó chuyển hướng đến trang "home"
		System.out.print(currentPrincipalName);
		///
		return "home";
	}
// Phương thức này xử lý yêu cầu GET đến "/registerPerfome"
	@GetMapping("/registerPerfome")
	// đối tượng RegisterForm đc thêm vào model để sử dụng trong form đăng ký và trả về chuỗi "registerPerfome", là tên của view để hiển thị form đăng ký cho người dùng
	String register(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "registerPerfome";
	}
// Phương thức này xử lý yêu cầu POST đến "/registerPerfome"
	@PostMapping("/registerPerfome")
	// nhận dữ liệu từ form đăng ký (registerForm), thực hiện kiểm tra hợp lệ (@Valid) và lưu kết quả vào BindingResult
	public String registerUser(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult,
			Model model) {

		// Nếu mật khẩu và mật khẩu nhập lại không khớp, thông báo lỗi sẽ được thêm vào bindingResult
		if (!registerForm.isPasswordsMatch()) {
			bindingResult.rejectValue("againPassword", "error.userForm", "Mật khẩu nhập lại không khớp");
		}
		// Nếu có lỗi xảy ra (bindingResult.hasErrors()), nó sẽ trả về "registerPerfome" (trang đăng ký) với model chứa registerForm và thông báo lỗi

		if (bindingResult.hasErrors()) {
			model.addAttribute("registerForm", registerForm);
			return "registerPerfome";
		}
		// Nếu không có lỗi, nó tạo một đối tượng User từ dữ liệu registerForm, gọi userService.registerUser(user) để đăng ký người dùng vào hệ thống
		User user = new User();
		user.setUsername(registerForm.getUsername());
		user.setPassword(registerForm.getPassword());
		user.setAddress(registerForm.getAddress());
		user.setDob(registerForm.getDob());
		user.setEmail(registerForm.getEmail());
		if (userService.registerUser(user)) {
			// Nếu đăng ký thành công, chuyển hướng đến "loginPerfome", ngược lại, trả về "registerPerfome" để người dùng có thể điều chỉnh thông tin đăng ký
		return "loginPerfome";
		}
		else {
			bindingResult.rejectValue("username", "error.username", "Tên người dùng đã tồn tại");
			model.addAttribute("registerForm", registerForm);
			return "registerPerfome";
		}
	

	}

//	@PostMapping("/registerPerfome")
//	ModelAndView registerPost(@RequestParam("username") String username, @RequestParam("password") String password,
//			RedirectAttributes redirectAttributes) {
//		// Đăng ký user
//		if (userService.registerUser(username, password)) {
//			return new ModelAndView("/loginPerfome");
//		}
//		ModelAndView model =new ModelAndView();
//		model.addObject("error","Đăng ký thất bại. Vui lòng thử lại.");
//		return model;
//	}
// Phương thức này xử lý yêu cầu GET đến "/logoutPerfome"
	@GetMapping("/logoutPerfome")
	// lấy thông tin về người dùng đã đăng nhập từ SecurityContextHolder và đăng xuất bằng SecurityContextLogoutHandler
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "loginPerfome"; // Sau khi đăng xuất, chuyển hướng người dùng đến trang "loginPerfome" để đăng nhập lại.
	}

}