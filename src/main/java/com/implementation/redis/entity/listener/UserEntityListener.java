package com.implementation.redis.entity.listener;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;

import com.implementation.redis.entity.User;
import com.implementation.redis.redisrepository.RedisRepository;

public class UserEntityListener {

	@Autowired
	private RedisRepository redisRepository;
	
	@PostPersist
	public void userPostPersist(User user) {
		this.redisRepository.save(user);
	}
	
	
}
