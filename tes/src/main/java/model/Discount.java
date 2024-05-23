package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {
	@Id
	String code;
	@Column
	int value;

	public Discount(String code, int value) {
		super();
		this.code = code;
		this.value = value;
	}

	public Discount() {
	}

	public String getCode() {
		return code;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Discount [code=" + code + ", value=" + value + "]";
	}
}
