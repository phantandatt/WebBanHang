package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Product;
import respository.ProductRespository;

@Service
@Transactional
public class ProductService {
	@Autowired(required = true)
	private ProductRespository resp;

	public void save(Product product) {
		resp.save(product);

	}

	public List<Product> getAllProd() {
		return resp.findAll();
	}

	public long totalProduct() {
		return resp.count();
	}
}
