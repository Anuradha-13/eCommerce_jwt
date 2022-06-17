package com.shopping.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {

	private int id;
	
	@NotBlank(message = "Product name is required.")
	private String name;

	@NotNull(message = "Price can not be Null.")
	private double price;

}
