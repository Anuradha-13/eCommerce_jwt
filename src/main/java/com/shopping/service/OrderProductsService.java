package com.shopping.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.shopping.entity.OrderProducts;

@Validated
public interface OrderProductsService {

	OrderProducts create(@NotNull @Valid OrderProducts orderProducts);

}
