package com.framework.boot.config;


import org.springframework.cache.CacheManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	private static final String[] CACHE_NAMES = { "shiro", "dict" };

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(CACHE_NAMES);
	}

	// @Bean
//	public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
//		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//		ObjectMapper objectMapper = JsonUtils.getObjectMapper().copy();
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
//		return RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(cacheConfiguration).build();
//	}
//
//	// @Bean
//	public RedisConfiguration redisConfiguration() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName("localhost");
//		redisStandaloneConfiguration.setPort(6379);
//		redisStandaloneConfiguration.setPassword("7777777");
//		return redisStandaloneConfiguration;
//	}
//
//	// @Bean
//	public LettuceClientConfiguration lettuceClientConfiguration() {
//		return LettuceClientConfiguration.builder().build();
//	}
//
//	// @Bean
//	public LettuceConnectionFactory lettuceConnectionFactory(RedisConfiguration redisConfiguration, LettuceClientConfiguration lettuceClientConfiguration) {
//		return new LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
//	}
}