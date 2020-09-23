package com.implementation.redis.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implementation.redis.dto.UserDTO;
import com.implementation.redis.entity.User;
import com.implementation.redis.exception.InternalException;
import com.implementation.redis.exception.NotFoundException;
import com.implementation.redis.redisRepository.RedisRepository;
import com.implementation.redis.repository.UserRepository;
import com.implementation.redis.service.UserService;

@Service()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RedisRepository redisRepository;

	@Override
	public List<UserDTO> findAllUser() {
		List<User> lstUserRegistered = this.redisRepository.findAll();
		return new ModelMapper().map(lstUserRegistered, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	@Override
	public List<UserDTO> findAllByRangeBirth(String from, String to) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date fromDate = dateFormat.parse(from);
			Date toDate = dateFormat.parse(to);
			List<User> userRegisteredInRangeDate = this.redisRepository.findAllByRangeBirth(fromDate, toDate);
			return new ModelMapper().map(userRegisteredInRangeDate, new TypeToken<List<UserDTO>>() {
			}.getType());

		} catch (ParseException e) {
			throw new InternalException("The date format entered must be [dd-MM-yyyy]");
		}
	}

	@Override
	public UserDTO newUser(UserDTO user) {
		User userRegistered = this.redisRepository.findByEmailUser(user.getEmailUser());
		if (userRegistered == null) {
			User userNew = new User();
			BeanUtils.copyProperties(user, userNew);
			userNew = this.userRepository.save(userNew);
			this.redisRepository.save(userNew);
			BeanUtils.copyProperties(userNew, user);
			return user;
		}
		throw new InternalException("The user is already registered");

	}

	@Override
	public String deleteUserByUsername(String emailUser) {
		User userRegistered = this.redisRepository.findByEmailUser(emailUser);
		if (userRegistered != null) {
			this.userRepository.delete(userRegistered);
			this.redisRepository.delete(userRegistered);
			return "This user has been deleted";
		}
		throw new InternalException("The user is is not registered");
	}

	@Override
	public UserDTO findByEmailUser(String emailUser) {
		User userRegistered = this.redisRepository.findByEmailUser(emailUser);

		UserDTO userReturned = new UserDTO();
		if (userRegistered != null) {
			BeanUtils.copyProperties(userRegistered, userReturned);
			return userReturned;
		}
		throw new NotFoundException("User not found");

	}

}
