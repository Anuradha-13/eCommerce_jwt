package com.shopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dto.ProductDto;
import com.shopping.entity.Product;
import com.shopping.exception.ProductNotFoundException;
import com.shopping.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream().map(prod -> modelMapper.map(prod, ProductDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new ProductNotFoundException("Product does not exist");
		}
	}

	@Override
	public List<ProductDto> getProductByName(String name) {
		return productRepository.findByNameIgnoreCaseContaining(name).stream()
				.map(prod -> modelMapper.map(prod, ProductDto.class)).collect(Collectors.toList());

	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product savedProduct = productRepository.save(product);
		ProductDto productResponse = modelMapper.map(savedProduct, ProductDto.class);

		return productResponse;
	}

	@Override
	public ProductDto updateProduct(int id, ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		productRepository.save(existingProduct);
		ProductDto productResponse = modelMapper.map(existingProduct, ProductDto.class);
		return productResponse;
	}

	@Override
	public void deleteProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Invalid Product Id."));
		productRepository.delete(product);
	}

}
