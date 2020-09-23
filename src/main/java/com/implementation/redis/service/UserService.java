package com.implementation.redis.service;

import java.util.List;

import com.implementation.redis.dto.UserDTO;

public interface UserService {
	List<UserDTO> findAllUser();
	UserDTO findByEmailUser(String emailUser);
	List<UserDTO> findAllByRangeBirth(String from, String to);
	UserDTO newUser(UserDTO user);
	String deleteUserByUsername(String emailUser);
}
