package com.martin.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

  @Value("${spring.data.redis.host}")
  private String host;
  @Value("${spring.data.redis.port}")
  private Integer port;
  @Value("${spring.data.redis.password}")
  private String password;

  @Bean
  LettuceConnectionFactory connectionFactory(){
    RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
    System.out.println(host +" - " +port+" - "+ password);
    redisConfig.setHostName(host);
    redisConfig.setPort(port);
    redisConfig.setPassword(password);
    return new LettuceConnectionFactory(redisConfig);
  }

  @Bean
  RedisTemplate<String ,String > redisTemplate(){
    RedisTemplate<String ,String > template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    return template;
  }
}
