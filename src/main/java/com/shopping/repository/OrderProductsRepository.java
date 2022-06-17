package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.OrderProducts;
import com.shopping.entity.OrderProductsPK;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, OrderProductsPK> {

}
