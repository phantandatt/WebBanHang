package com.tandat.webdemo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterForm {


	@NotBlank(message = "Tên tài khoản không được để trống")
	@Size(min = 5, max = 15, message = "Tên tài khoản phải từ 5 đến 15 ký tự")
	private String username;

	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(min = 8, message = "Mật khẩu phải chứa ít nhất 8 ký tự")
	private String password;

	@NotBlank(message = "Nhập lại mật khẩu không được để trống")
	private String againPassword;

	@NotNull(message = "Ngày sinh không được để trống")
	@Past(message = "Ngày sinh phải là một ngày trong quá khứ")
	private LocalDate dob;

	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không hợp lệ")
	private String email;

	@NotBlank(message = "Địa chỉ không được để trống")
	private String address;

	// Getters and setters

	// Phương thức này kiểm tra xem password và againPassword có khớp nhau hay không
	public boolean isPasswordsMatch() {
		return password != null && password.equals(againPassword);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgainPassword() {
		return againPassword;
	}

	public void setAgainPassword(String againPassword) {
		this.againPassword = againPassword;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
