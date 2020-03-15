package com.product.rizvan.products_mobile_api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.product.rizvan.products_mobile_api.DTO.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto updateUser(String userId, UserDto userDto);
	String deleteUser(String userId);
	List<UserDto> getUsers();
	UserDto getUser(String userName);
}
