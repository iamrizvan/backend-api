package com.product.rizvan.products_mobile_api.security;

import com.product.rizvan.products_mobile_api.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 36000; // 10 days;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user";
	public static final String CREATE_PRODUCT_URL = "/products";
	public static final String H2_CONSOLE = "/h2-console/**";
	
	public static String getTokenSecret()
	{
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
