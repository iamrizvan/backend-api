package com.product.rizvan.products_mobile_api.repository;

import org.springframework.data.repository.CrudRepository;

import com.product.rizvan.products_mobile_api.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	UserEntity findByUserId(String userId);
	UserEntity findByEmail(String email);
	void deleteByUserId(String userId);
}
