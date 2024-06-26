package com.tandat.webdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tandat.webdemo.model.Product;
import com.tandat.webdemo.respository.ProductRespository;

@Service
@Transactional
public class ProductService {
	@Autowired(required = true)
	private ProductRespository resp;

	public void save(Product product) {
		resp.save(product);

	}

	public List<Product> searchProducts(String keyword) {
		return resp.findByProductName(keyword);
	}

	public List<Product> getAllProd() {
		return resp.findAll();
	}

	public Page<Product> getAllProd(int page) {
		Pageable pageable = PageRequest.of(page, 9);
		return resp.findAll(pageable);
	}

	public long totalProduct() {
		return resp.count();
	}

	public Product findById(int productId) {
		// TODO Auto-generated method stub
		return resp.findById(productId);
	}
	public void deleteById(int productId) {
		resp.deleteById(productId);
	}
}
