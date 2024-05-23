package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "dob")
	private String dob;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "role")
	private int role;

	public Account(String username, String password, String dob, String email, String address, int role) {
		super();
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.email = email;
		this.address = address;
		this.role = role;
	}

	public Account() {

	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public int getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", dob=" + dob + ", email=" + email
				+ ", address=" + address + ", role=" + role + "]";
	}

}