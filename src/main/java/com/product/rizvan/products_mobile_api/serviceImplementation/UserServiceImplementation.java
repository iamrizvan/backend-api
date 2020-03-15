package com.product.rizvan.products_mobile_api.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.product.rizvan.products_mobile_api.DTO.UserDto;
import com.product.rizvan.products_mobile_api.entity.UserEntity;
import com.product.rizvan.products_mobile_api.io.Utils;
import com.product.rizvan.products_mobile_api.repository.UserRepository;
import com.product.rizvan.products_mobile_api.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	// This method is used for Spring security for authentication.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	
	// Create New User
	@Override
	public UserDto createUser(UserDto user) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userEntity.setUserId(new Utils().generateRandomId(30));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity createdUserEntity = userRepo.save(userEntity);
		UserDto generatedUser = modelMapper.map(createdUserEntity, UserDto.class);
		return generatedUser;
	}
	



	// Get User by email ...  this method is required for authorization.
	@Override
	public UserDto getUser(String email) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepo.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto generatedUser = modelMapper.map(userEntity, UserDto.class);
		return generatedUser;
	}

	



	// Update User
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepo.findByUserId(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		UserEntity returnEntity = userRepo.save(userEntity);
		return modelMapper.map(returnEntity, UserDto.class);
	}




	// Delete User
	@Override
	public String deleteUser(String userId) {
		UserEntity userEntity = userRepo.findByUserId(userId);
		if(userEntity == null)
		{
			return "Product not found.";
		}
		else
		{
			userRepo.delete(userEntity);
			return "User has been deleted successfully.";	
		}
	}


	@Override
	public List<UserDto> getUsers() {
		ModelMapper modelMapper = new ModelMapper();
		List<UserDto> usersDto = new ArrayList<>();
		List<UserEntity> usersEntity = new ArrayList<>();
		userRepo.findAll().forEach(item -> usersEntity.add(item));
		for(UserEntity userEntity : usersEntity)
		{
			UserDto userDto = modelMapper.map(userEntity, UserDto.class);
			usersDto.add(userDto);
		}
		return usersDto;
	}


	
}
