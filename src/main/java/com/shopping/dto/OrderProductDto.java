package com.shopping.dto;

import javax.validation.constraints.NotNull;

import com.shopping.entity.Product;

import lombok.Data;

public class OrderProductDto {

	private Product product;

	
	
	@NotNull
	private int quantity;

	
	
	public OrderProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderProductDto(Product product, @NotNull int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
