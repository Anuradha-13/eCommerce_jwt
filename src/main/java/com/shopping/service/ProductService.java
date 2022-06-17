package com.shopping.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.shopping.dto.ProductDto;
import com.shopping.entity.Product;

@Validated
public interface ProductService {

	List<ProductDto> getAllProducts();

	Product getProductById(int id);

	List<ProductDto> getProductByName(String name);

	ProductDto createProduct(ProductDto productDto);

	ProductDto updateProduct(int id, ProductDto productDto);

	void deleteProduct(int id);

}
