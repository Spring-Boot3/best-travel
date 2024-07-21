package com.debuggeando_ideas.best_travel.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

    @Value(value = "cache.redis.address")
    private String serverAdress;
    @Value(value = "cache.redis.password")
    private String serverPassword;

    @Bean
    public RedissonClient redissonClient () {
        var config = new Config();
        config.useSingleServer()
                .setAddress(serverAdress)
                .setPassword(serverPassword);
        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient) {
        var config = new HashMap<String, String>();
        return new RedissonSpringCacheManager(redissonClient, String.valueOf(config));
    }

}
