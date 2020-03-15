package com.product.rizvan.products_mobile_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.product.rizvan.products_mobile_api.entity.ProductEntity;

@Repository
public interface ProductRepository extends  CrudRepository<ProductEntity, Integer> {
	
	 ProductEntity findById(int id);
	 ProductEntity findByProductId(String productId);

}
