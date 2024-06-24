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

    // Custom validation to check if password and againPassword match
    public boolean isPasswordsMatch() {
        return password != null && password.equals(againPassword);
    }
}
