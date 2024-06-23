package com.tandat.webdemo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tandat.webdemo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
