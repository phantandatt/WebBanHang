package com.tandat.webdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tandat.webdemo.model.Cart;
import com.tandat.webdemo.respository.CartRespository;

@Service

public class CartService {

	
	@Autowired
	CartRespository cartRespository;
	

	public List<Cart> getCartByUsername(String username){
	
		return cartRespository.findByUsername(username);
	}


	public void save(Cart newCart) {
		// TODO Auto-generated method stub
		cartRespository.save(newCart);
	}


	public void updateSoLuong(Long id_cart,int amount) {
		// TODO Auto-generated method stub
		cartRespository.update(id_cart,amount);
	}
	
	public boolean contain(List<Cart> carts, int productID) {
		for (Cart cart : carts) {
			if(cart.getProductID() == productID)
				return true;
		}
		return false;
	}


	public void deleteCart(Long idCart) {
		// TODO Auto-generated method stub
		cartRespository.deleteById(idCart);
	}


	public void increaseCart(Long idCart, int soluong) {
		// TODO Auto-generated method stub
		cartRespository.increaseCart(idCart,soluong);
	}
}
