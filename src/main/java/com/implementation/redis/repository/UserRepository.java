package com.implementation.redis.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.implementation.redis.entity.User;

@Repository()
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmailUser(String emailUser);
	List<User> findAllByBirthDateBetween(Date from, Date to);
}
