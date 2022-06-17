package com.shopping.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.ECommerceAppApplication;
import com.shopping.dto.ProductDto;
import com.shopping.entity.Product;
import com.shopping.exception.ErrorResponse;
import com.shopping.service.ProductService;
import com.shopping.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECommerceAppApplication.class);

	@Autowired
	private ModelMapper modelMapper;

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/")
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "id") int id) {
		
		return ResponseEntity.ok().body(productService.getProductById(id));
	}

	@GetMapping("/name/{name}")
	public List<ProductDto> getProductByName(@PathVariable(name = "name") String name) {

		return productService.getProductByName(name);

	}

	@PostMapping("/")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

		return new ResponseEntity<ProductDto>(productService.createProduct(productDto), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updatePost(@PathVariable int id, @RequestBody ProductDto productDto) {

		return ResponseEntity.ok().body(productService.updateProduct(id, productDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ErrorResponse> deletePost(@PathVariable(name = "id") int id) {
		productService.deleteProduct(id);
		ErrorResponse erorResponse = new ErrorResponse(HttpStatus.OK, "Poduct deleted successfully");
		return new ResponseEntity<ErrorResponse>(erorResponse, HttpStatus.OK);
	}

}
