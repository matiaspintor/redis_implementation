package com.implementation.redis.redisrepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.implementation.redis.entity.User;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

	private static final String KEY = "USERS";
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, User> redisTemplate;

	private HashOperations<String, String, User> hashOperations;

	

	@PostConstruct
	public void init() {
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		this.hashOperations.put(KEY, user.getEmailUser(), user);
	}

	@Override
	public void saveAll(Map<String,User> mapUsers) {
		this.hashOperations.putAll(KEY, mapUsers);
	}

	@Override
	public void delete(User user) {
		this.hashOperations.delete(KEY, user.getEmailUser());

	}

	@Override
	public List<User> findAllByRangeBirth(Date from, Date to) {
		return this.hashOperations.values(KEY).stream()
				.filter(x -> x.getBirthDate().after(from) && x.getBirthDate().before(to)).collect(Collectors.toList());
	}

	@Override
	public List<User> findAll() {
		return this.hashOperations.values(KEY);
	}

	@Override
	public User findByEmailUser(String emailUser) {
		return this.hashOperations.values(KEY).stream()
				.filter(x -> x.getEmailUser().equals(emailUser)).findFirst().orElse(null);
	}

}
