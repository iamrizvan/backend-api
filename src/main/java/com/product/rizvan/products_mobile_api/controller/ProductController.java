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
import com.product.rizvan.products_mobile_api.service.ProductService;
import com.product.rizvan.products_mobile_api.service.UserService;

@RestController
@RequestMapping // http://localhost:8080/
public class ProductController {

	@Autowired
	ProductService service;
	
	

	@GetMapping("/products")
	public List<Product> getProducts() {
		ModelMapper modelMapper = new ModelMapper();
		List<Product> returnValue = new ArrayList<Product>();
		List<ProductDTO> products = service.getProducts();
		for (ProductDTO productDTO : products) {
			Product product = modelMapper.map(productDTO, Product.class);
			returnValue.add(product);
		}
		return returnValue;
	}

	
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody ProductRequestModel product){
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
		ProductDTO createdProduct = service.createProduct(productDto);
		Product returnValue = modelMapper.map(createdProduct, Product.class);
		return returnValue;
	}
	
	
	@PutMapping("/products/{productId}")
	public Product updateProduct(@PathVariable int productId, @RequestBody ProductRequestModel updatedProduct) {
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO productDto = modelMapper.map(updatedProduct, ProductDTO.class);
		ProductDTO createdProduct = service.updateProduct(productId, productDto);
		Product returnValue = modelMapper.map(createdProduct, Product.class);
		return returnValue;
	}
	
	@DeleteMapping("/products/{productId}")
	public String updateProduct(@PathVariable String productId) {
		String result = service.deleteProduct(productId);
		return result;
	}

}
