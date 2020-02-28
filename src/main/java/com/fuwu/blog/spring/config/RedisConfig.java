package com.fuwu.blog.spring.config;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fuwu.blog.constant.CacheConstant;


/**
 * 
 * Redis config:
 * spring-boot-starter-data-redis  provides basic auto configurations for 
 * Lettuce  and Jedis client libraries，it resolves Lettuce by default. 
 * Spring provides LettuceConnectionFactory to get connections.
 * To get pooled connection factory we need to provide commons-pool2 on the classpath
 * 
 * Cache config:
 * In Spring Boot, if redis is available and configured in our Spring Boot application,
 * RedisCacheManager will be auto-configured.
 * we can config redis cache by:
 * 1.Config Spring Boot redis cache properties to change its default value for auto-configured RedisCacheManager
 * 2.or we can create a sample own RedisCacheManager to get full control on configurations.
 * 
 * see:
 * https://www.concretepage.com/spring-boot/spring-boot-redis-cache
 * 
 * @author fuwu
 *
 */

@Configuration
@EnableCaching
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, Serializable> redisTemplate=new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}
   @Bean
   public RedisCacheConfiguration cacheConfiguration() {
	 //ClassCastException with Redis cache and devtools
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig(loader)
		.entryTtl(Duration.ofSeconds(600))  
	  .disableCachingNullValues();	
	return cacheConfig;
   }
   
   @Bean
   public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
	RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory)
	  .cacheDefaults(cacheConfiguration())
	  .transactionAware()
	  .build();
	return rcm;
   }

}
