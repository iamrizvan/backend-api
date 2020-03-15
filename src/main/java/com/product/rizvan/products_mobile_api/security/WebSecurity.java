package com.product.rizvan.products_mobile_api.security;

import javax.ws.rs.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.product.rizvan.products_mobile_api.service.UserService;
import com.product.rizvan.products_mobile_api.serviceImplementation.UserServiceImplementation;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
	//	.addFilter(new AuthenticationFilter(authenticationManager()));  Use if you don't want custom login URL.
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Optional way to add custom login URL.
		
		
		http.headers().frameOptions().disable();
		
	}
	
	// Below code snippet is for allowUrlEncodedSlashHttpFirewall
	/*
	 * public HttpFirewall allowUrlEncodedSlashHttpFirewall() { StrictHttpFirewall
	 * firewall = new StrictHttpFirewall(); firewall.setAllowUrlEncodedSlash(true);
	 * return firewall; }
	 * 
	 * 
	 * public void
	 * configure(org.springframework.security.config.annotation.web.builders.
	 * WebSecurity web) throws Exception { super.configure(web);
	 * web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	 * 
	 * }
	 */

	@Override protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception { auth.userDetailsService(userService)
	  .passwordEncoder(bCryptPasswordEncoder);
	  }
	
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");  // Customise authentication URL.
		return filter;
	}
	 	
}
