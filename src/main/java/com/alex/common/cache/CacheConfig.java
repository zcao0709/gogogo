package com.alex.common.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by caozhennan on 2017/3/12.
 */
@Configuration
public class CacheConfig {
    @Value("${redis.cluster}")
    private String cluster;

    @Value("${redis.timeout}")
    private int timeout;

    public JedisConnectionFactory jedisConnectionFactory() {
//        List<String> nodes = Arrays.asList(cluster.split(","));
//        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(nodes);
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfig);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setTimeout(timeout);
        connectionFactory.afterPropertiesSet();

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new GenericToStringSerializer(Object.class));
//
//        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jsonRedisSerializer.setObjectMapper(om);
//        redisTemplate.setValueSerializer(jsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
