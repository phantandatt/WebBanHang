package com.tandat.webdemo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tandat.webdemo.model.Discount;
@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {

}
