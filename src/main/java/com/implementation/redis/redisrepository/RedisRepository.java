package com.implementation.redis.redisrepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.implementation.redis.entity.User;

public interface RedisRepository {
	void save(User user);

	void saveAll(Map<String,User> lstUser);

	void delete(User user);

	List<User> findAllByRangeBirth(Date from, Date to);

	List<User> findAll();

	User findByEmailUser(String emailUser);
}
