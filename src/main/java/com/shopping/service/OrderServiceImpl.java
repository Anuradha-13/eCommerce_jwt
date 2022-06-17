package com.shopping.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Service;

import com.shopping.ECommerceAppApplication;
import com.shopping.dto.OrderProductDto;
import com.shopping.entity.Order;
import com.shopping.entity.OrderProducts;
import com.shopping.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
		
	private OrderRepository orderRepository;
	private ProductService productService;

	public OrderServiceImpl(OrderRepository orderRepository,ProductService productService) {
		this.orderRepository = orderRepository;
		this.productService= productService;
	}

		public Order createOrder(List<OrderProductDto> orderProductDto,int userid) {
				
				//Create order
				Order order = new Order();
				order.setDateCreated(LocalDate.now());
				
				//Add products to order
				List<OrderProducts> orderProducts = new ArrayList<>();
				
				for (OrderProductDto dto : orderProductDto) {
					orderProducts.add(
							new OrderProducts(order, productService.getProductById(dto.getProduct().getId()),dto.getQuantity()));
						        
				}
	
				//Save final order
				order.setOrderProducts(orderProducts);
				order.setUserId(userid);
				System.out.println("oder created::"+order);
				return this.orderRepository.save(order);


	}

	//Using Jpa methods
	@Override
	public List<Order> listByLastMonthOrders() {
		LocalDate now = LocalDate.now(); 
	    LocalDate startDate = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()); 
	    LocalDate endDate = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
	   
		return orderRepository.findAllByDateCreatedBetween(startDate, endDate);
	}
	
	

	//Using Spring data Jpa query
	@Override
	public List<Order> findAllByDateCreatedLastMonthUsingJpa() {
		LocalDate now = LocalDate.now();
		
		LocalDate dateCreatedDateTime = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		return orderRepository.findAllWithDateCreatedTimeBefore(dateCreatedDateTime);
	}

	//Using Native Query

	/*
	 * @Override public List<Order> findAllByDateCreatedLastMonth() {
	 * 
	 * DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 * LocalDate now = LocalDate.now(); LocalDate start =
	 * now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()); LocalDate end =
	 * now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
	 * 
	 * return orderRepository.getAllBetweenDates(start, end); }
	 */	

	
}
