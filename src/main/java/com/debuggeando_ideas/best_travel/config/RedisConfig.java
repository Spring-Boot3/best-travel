package com.debuggeando_ideas.best_travel.config;

import com.debuggeando_ideas.best_travel.util.constants.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

// Esta anotacion habilita la configuracion de spring
@Configuration
// Esta anotacion habilita el uso de cache en spring
@EnableCaching
// Esta anotacion habilita la programacion de tareas en spring
@EnableScheduling
// Esta amotacion habilita la configuracion de spring
@Slf4j
// Esta clase de configuracion se encarga de configurar el cliente de redis y el cache manager
public class RedisConfig {

    @Value(value = "cache.redis.address")
    private String serverAddress;
    @Value(value = "cache.redis.password")
    private String serverPassword;

    /*
     * Con esta configuracion cargamos el cliente de redis al contenedor de spring
     */
    @Bean
    public RedissonClient redissonClient() {
        var config = new Config();
        config.useSingleServer()
                .setAddress(serverAddress)
                .setPassword(serverPassword);
        return Redisson.create(config);
    }

    /*
     * Con esta configuracion podemos habilitar las anotaciones de spring cache @Cacheable
     */
    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient) {
        var configs = Map.of(
                CacheConstants.FLY_CACHE_NAME, new CacheConfig(),
                CacheConstants.HOTEL_CACHE_NAME, new CacheConfig()
        );
        return new RedissonSpringCacheManager(redissonClient, configs);
    }

    /*
     * Con esta configuracion podemos programar tareas en spring
     */
    // Esta anotacion es para limpiar el cache de redis
    @CacheEvict(
            cacheNames = {CacheConstants.FLY_CACHE_NAME, CacheConstants.HOTEL_CACHE_NAME},
            allEntries = true
    )
    @Scheduled(cron = CacheConstants.SCHEDULED_RESET_CACHE)
    // Esta anotacion habilita la ejecucion de metodos
    // de forma asincrona lo que permite que el metodo
    // se ejecute en un hilo diferente
    @Async
    public void deleteCache() {
        log.info("Cache deleted");

    }

}
