package com.tandat.webdemo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// được quản lý bởi JPA (Java Persistence API).
@Entity
// tên bảng account entity sẽ ánh xạ tới
@Table(name = "account")

@Data // lombok, tự động tạo các getter, setter, phương thức toString(), equals(), và hashCode()
// Cung cấp một builder pattern cho việc khởi tạo đối tượng
@Builder
// Tạo các constructors không có tham số và có tham số cho entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
	// id primary key của bảng
	@Id 
	// Được sử dụng để tự động tạo giá trị cho trường id
	@GeneratedValue
	private Long id;
// là một cột trong bảng với ràng buộc unique và không cho phép null
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	private LocalDate dob;
	private String email;
	private String address;
	private int role;

	public Long getId() {
		return id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
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
