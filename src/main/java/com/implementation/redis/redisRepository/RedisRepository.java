package com.implementation.redis.redisRepository;

import java.util.Date;
import java.util.List;

import com.implementation.redis.entity.User;

public interface RedisRepository {
	void save(User user);

	void saveAll(List<User> lstUser);

	void delete(User user);

	List<User> findAllByRangeBirth(Date from, Date to);

	List<User> findAll();

	User findByEmailUser(String emailUser);
}
