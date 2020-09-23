package com.implementation.redis.redisRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.implementation.redis.entity.User;
import com.implementation.redis.repository.UserRepository;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

	private static final String KEY = "USERS";
	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	private HashOperations<String, String, User> hashOperations;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		this.hashOperations = this.redisTemplate.opsForHash();
		List<User> lstAllUsersInH2 = this.userRepository.findAll();
		this.saveAll(lstAllUsersInH2);
	}

	@Override
	public void save(User user) {
		this.hashOperations.put(KEY, user.getEmailUser(), user);
	}

	@Override
	public void saveAll(List<User> lstUser) {
		Map<String, User> hashMapUser = new HashMap<>();
		for (User user : lstUser) {
			hashMapUser.put(user.getEmailUser(), user);
		}
		this.hashOperations.putAll(KEY, hashMapUser);
	}

	@Override
	public void delete(User user) {
		this.hashOperations.delete(KEY, user.getEmailUser());

	}

	@Override
	public List<User> findAllByRangeBirth(Date from, Date to) {
		List<User> lstReturn = (List<User>) this.hashOperations.values(KEY).stream()
				.filter(x -> x.getBirthDate().after(from) && x.getBirthDate().before(to)).collect(Collectors.toList());
		return lstReturn;
	}

	@Override
	public List<User> findAll() {
		List<User> lstReturn = (List<User>) this.hashOperations.values(KEY);
		return lstReturn;
	}

	@Override
	public User findByEmailUser(String emailUser) {
		User userFinded = (User) this.hashOperations.values(KEY).stream()
				.filter(x -> x.getEmailUser().equals(emailUser)).findFirst().orElse(null);
		return userFinded;
	}

}
