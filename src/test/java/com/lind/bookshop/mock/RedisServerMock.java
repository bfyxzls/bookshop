package com.lind.bookshop.mock;

import com.lind.bookshop.util.RandomUtils;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.embedded.RedisServer;

/**
 * redis模拟.
 */
@Configuration
@Profile("test")
public class RedisServerMock {

  private RedisServer redisServer;
  private String redisHost = "localhost";
  private int redisPort = RandomUtils.getRandomInt(58000, 60000);

  @Bean
  RedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration =
        new RedisStandaloneConfiguration(redisHost, redisPort);
    RedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration);
    return factory;
  }

  @PostConstruct
  public void startRedis() throws IOException {
    redisServer = new RedisServer(redisPort);
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    redisServer.stop();
  }
}
