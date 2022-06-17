package com.shopping.service;

import java.util.List;

import com.shopping.dto.UserDto;
import com.shopping.entity.Order;
import com.shopping.entity.User;

public interface UserService {
	
	public List<Order> getAllOrders(int userid);

	public User findOne(String username);
	
    User save(UserDto user);

}
