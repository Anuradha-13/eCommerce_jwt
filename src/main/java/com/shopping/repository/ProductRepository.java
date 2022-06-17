package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.dto.ProductDto;
import com.shopping.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	// Optional<Product> findByName(String name);
	List<Product> findByNameIgnoreCaseContaining(String name);

	

}
