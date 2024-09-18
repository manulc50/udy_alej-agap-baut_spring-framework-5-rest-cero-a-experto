package com.devs4j.users.configs;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Nota: Descomentar esta anotación si se desea usar Spring Cache con Redis
//@Configuration // Anotamos esta clase de configuración como una clase de configuración de Spring
@EnableCaching
public class CacheConfig {
	
	// Método que crea un administrador de cachés y lo devuelve como un bean de Spring
	// Si queremos crear la caché en Redis, tenemos que pasar a este método como argumento de entrada un cliente de tipo RedissonClient. Este argumento de entrada es inyectado por Spring usando el bean creado en el método de abajo "redisson"
	// Si queremos crear la caché directamente en memoria, no hay que pasar ningún argumento de entrada a este método
	@Bean
	public CacheManager getCache(RedissonClient redissonClient) {
		// return new ConcurrentMapCacheManager("users"); // Para que la caché se ejecute en memoria
		// Para que la caché se ejecute en Redis
		Map<String, CacheConfig> config = new HashMap<>();
		config.put("users",new CacheConfig()); // Crea una caché llamada "users"
		// Usa el administrador de cachés por defecto de Redisson
		return new RedissonSpringCacheManager(redissonClient);
	}
	
	// Método que crea un cliente Redisson, para que esta aplicación pueda conectarse a Redis, y lo almacena como un bean de Spring
	// Se invoca a su método "shutdown" justo antes de destruirse el bean
	@Bean(destroyMethod ="shutdown") // "destroyMethod" es el callback de PreDestroy
	public RedissonClient redisson() {
		Config config = new Config();
		// Se conecta a un único servidor de Redis que se ejecuta en el puerto 6379 de localhost
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		return Redisson.create(config);
	}

}
