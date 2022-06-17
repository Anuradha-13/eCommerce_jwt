package com.shopping.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shopping.ECommerceAppApplication;
import com.shopping.dto.OrderProductDto;
import com.shopping.dto.ProductDto;
import com.shopping.entity.Order;
import com.shopping.entity.OrderProducts;
import com.shopping.entity.Product;
import com.shopping.exception.ProductNotFoundException;
import com.shopping.service.OrderProductsService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ECommerceAppApplication.class);
	@Autowired
	private ModelMapper modelMapper;

	OrderService orderService;
	ProductService productService;
	OrderProductsService orderProductsService;

	public OrderController(ProductService productService, OrderService orderService,
			OrderProductsService orderProductsService) {
		this.productService = productService;
		this.orderService = orderService;
		this.orderProductsService = orderProductsService;
	}

	@GetMapping("/lastMonth")
	public List<Order> listByLastMonthOrders() {
		
		LOGGER.info("List of Orders created last month");
		return this.orderService.listByLastMonthOrders();
	}

	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody List<OrderProductDto> orderProductDto, int userid) {
		LOGGER.info("Oder created with products");
		return new ResponseEntity<Order>(orderService.createOrder(orderProductDto, userid), HttpStatus.CREATED);

	}

}