package com.shopping.service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.shopping.entity.OrderProducts;
import com.shopping.repository.OrderProductsRepository;

@Service
@Transactional
public class OrderProductsServiceImpl implements OrderProductsService {

	private OrderProductsRepository orderProductsRepository;

	public OrderProductsServiceImpl(OrderProductsRepository orderProductsRepository) {
		this.orderProductsRepository = orderProductsRepository;
	}

	@Override
	public OrderProducts create(@NotNull @Valid OrderProducts orderProducts) {
		return this.orderProductsRepository.save(orderProducts);

	}

}
