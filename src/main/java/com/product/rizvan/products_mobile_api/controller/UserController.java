package com.product.rizvan.products_mobile_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.rizvan.products_mobile_api.DTO.ProductDTO;
import com.product.rizvan.products_mobile_api.DTO.UserDto;
import com.product.rizvan.products_mobile_api.request.ProductRequestModel;
import com.product.rizvan.products_mobile_api.request.UserRequest;
import com.product.rizvan.products_mobile_api.response.Product;
import com.product.rizvan.products_mobile_api.response.User;
import com.product.rizvan.products_mobile_api.service.UserService;

@RestController 
@RequestMapping
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		ModelMapper modelMapper = new ModelMapper();
		List<User> returnValue = new ArrayList<User>();
		List<UserDto> users = userService.getUsers();
		for (UserDto userDto : users) {
			User user = modelMapper.map(userDto, User.class);
			returnValue.add(user);
		}
		return returnValue;
	}
	
	@PostMapping("/user") // http://localhost:8080/users
	public User createUser(@RequestBody UserRequest user)
	{
		ModelMapper modelMapper  = new ModelMapper();
		UserDto requestDto = modelMapper.map(user, UserDto.class);
		UserDto createdUser =  userService.createUser(requestDto);
		User returnValue = modelMapper.map(createdUser, User.class);
		return returnValue;
	}
	
	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable String userId, @RequestBody UserRequest updateUser) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(updateUser, UserDto.class);
		UserDto createdUser = userService.updateUser(userId, userDto);
		User returnValue = modelMapper.map(createdUser, User.class);
		return returnValue;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable String userId) {
		String result = userService.deleteUser(userId);
		return result;
	}
	

}
