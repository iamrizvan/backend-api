package com.product.rizvan.products_mobile_api.request;

import java.io.Serializable;

public class UserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -369695783621056374L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
