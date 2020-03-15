package com.product.rizvan.products_mobile_api.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class ProductEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7754526357244190748L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="userId", nullable=false)
	private String userId;
	
	@Column(name="productId", nullable=false)
	private String productId;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description" , nullable = false)
	private String description;
	
	@Column(name="price", nullable=false)
	private String price;
	
	@Column(name="image", nullable=false)
	private String image;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean isFavorite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
}
