package com.tandat.webdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tandat.webdemo.model.Discount;
import com.tandat.webdemo.respository.DiscountRepository;

@Service

public class DiscountService {
	@Autowired
	private DiscountRepository discountRepository;

	public List<Discount> getAllDiscount() {
		return discountRepository.findAll();
	}

	public boolean checkDiscount(String code) {

		return discountRepository.findById(code) != null;
	}

	public List<Discount> getAllDiscounts() {
		return discountRepository.findAll();
	}

	public Optional<Discount> getDiscountById(String id) {
		return discountRepository.findById(id);
	}
	public Discount saveDiscount(String code, int value) {
		Discount dc = new Discount(code, value);
		return discountRepository.save(dc);
	}

	public Discount saveDiscount(Discount discount) {
		return discountRepository.save(discount);
	}

	public void deleteDiscount(String id) {
		discountRepository.deleteById(id);
	}
	public void updateValue(String code, int newValue) {
		discountRepository.updateVauleOfCode(code, newValue);
	}
}
