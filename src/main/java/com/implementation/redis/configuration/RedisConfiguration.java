package com.implementation.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.implementation.redis.entity.User;

@Configuration
@EnableRedisRepositories(basePackages = "com.implementation.redis.redisRepository")
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String hostRedis;

	@Value("${spring.redis.port}")
	private Integer portRedis;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandAloneConfiguration = new RedisStandaloneConfiguration(this.hostRedis,
				this.portRedis);
		// redisStandAloneConfiguration.setPassword(RedisPassword.of(""));
		return new JedisConnectionFactory(redisStandAloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

}
