package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.UserDto;
import com.shopping.entity.User;
import com.shopping.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signup(@RequestBody UserDto user) {
		return userService.save(user);
	}

}
