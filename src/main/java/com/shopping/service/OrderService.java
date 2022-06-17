package com.shopping.service;

import java.time.LocalDate;
import java.util.List;

import com.shopping.dto.OrderProductDto;
import com.shopping.entity.Order;

public interface OrderService {

	
	public List<Order> findAllByDateCreatedLastMonthUsingJpa();
	
	
	public List<Order> listByLastMonthOrders();
	
		
	public Order createOrder(List<OrderProductDto> orderProductDto,int userid);





}