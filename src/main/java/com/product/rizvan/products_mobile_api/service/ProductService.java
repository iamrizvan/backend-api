package com.product.rizvan.products_mobile_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.rizvan.products_mobile_api.DTO.ProductDTO;

public interface ProductService  {
	
	List<ProductDTO> getProducts();
	ProductDTO createProduct(ProductDTO product);
	ProductDTO updateProduct(int productId, ProductDTO productDto);
	String deleteProduct(String productId);

}
