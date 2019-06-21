package com.lind.bookshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 对redis.
 */
@Component
public class RedisConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  /**
   * redis重写RedisTemplate.
   */
  @Bean
  public RedisTemplate redisTemplate() {
    RedisTemplate redisTemplate = new RedisTemplate();
    RedisSerializer stringSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringSerializer);
    redisTemplate.setValueSerializer(stringSerializer);
    redisTemplate.setHashKeySerializer(stringSerializer);
    redisTemplate.setHashValueSerializer(stringSerializer);
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }
}
