package com.tandat.webdemo.respository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tandat.webdemo.model.Cart;

import jakarta.transaction.Transactional;

public interface CartRespository extends JpaRepository<Cart, Long> {

	 @Query("SELECT DISTINCT c FROM Cart c WHERE c.username = :username")
	public List<Cart> findByUsername( String username);
	 @Modifying
	    @Transactional
		@Query("UPDATE Cart c SET c.amount = :amount WHERE c.idCart = :id_cart")
	public void update(Long id_cart,int amount);
	 
	 @Modifying
	    @Transactional
		@Query("UPDATE Cart c SET c.amount = :soluong WHERE c.idCart = :idCart")
	public void increaseCart(Long idCart, int soluong);

}
