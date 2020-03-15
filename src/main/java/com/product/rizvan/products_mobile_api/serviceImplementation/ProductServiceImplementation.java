package com.product.rizvan.products_mobile_api.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.rizvan.products_mobile_api.DTO.ProductDTO;
import com.product.rizvan.products_mobile_api.entity.ProductEntity;
import com.product.rizvan.products_mobile_api.io.Utils;
import com.product.rizvan.products_mobile_api.repository.ProductRepository;
import com.product.rizvan.products_mobile_api.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	ProductRepository productRepo;

	public List<ProductDTO> getProducts() {
		ModelMapper modelMapper = new ModelMapper();
		List<ProductEntity> products = new ArrayList<>();			
		List<ProductDTO> productsDTO = new ArrayList<>();	
		productRepo.findAll().forEach(item -> products.add(item));
		for(ProductEntity productEntity : products)
		{
			ProductDTO productDTO =	modelMapper.map(productEntity, ProductDTO.class);
			productsDTO.add(productDTO);
		}
		return productsDTO;
	}

	public ProductDTO createProduct(ProductDTO product) {
		ModelMapper modelMapper = new ModelMapper();
		ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
		productEntity.setProductId(new Utils().generateRandomId(30));
		ProductEntity storedUserDetails = productRepo.save(productEntity);
		ProductDTO returnValue = modelMapper.map(storedUserDetails, ProductDTO.class);
		return returnValue;
	}

	@Override
	public ProductDTO updateProduct(int productId, ProductDTO productDto) {
		ModelMapper modelMapper = new ModelMapper();
		ProductEntity productEntity = productRepo.findById(productId);
		productEntity.setTitle(productDto.getTitle());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setImage(productDto.getImage());
		productEntity.setIsFavorite(productDto.getIsFavorite());
		ProductEntity returnEntity = productRepo.save(productEntity);
		return modelMapper.map(returnEntity, ProductDTO.class);
	}

	@Override
	public String deleteProduct(String productId) {
		ProductEntity productEntity = productRepo.findByProductId(productId);
		if(productEntity == null)
		{
			return "Product not found.";
		}
		else
		{
			productRepo.deleteById(productEntity.getId());
			return "User has been deleted successfully.";	
		}
	}

}
