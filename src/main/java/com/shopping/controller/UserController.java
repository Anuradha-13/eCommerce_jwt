package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.UserDto;
import com.shopping.entity.Order;
import com.shopping.entity.User;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

		
		
		@PostMapping
	    public ResponseEntity<User> saveUser(@RequestBody UserDto user){
	        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
	    }


		@GetMapping("/{userid}/orders")
		public ResponseEntity<List<Order>> list(@PathVariable(name = "userid") int userid) {
			
			List<Order> order = userService.getAllOrders(userid);
			return ResponseEntity.ok().body(order);
}

}
