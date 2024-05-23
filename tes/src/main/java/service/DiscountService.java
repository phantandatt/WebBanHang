package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Discount;
import respository.DiscountRepository;

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
}
