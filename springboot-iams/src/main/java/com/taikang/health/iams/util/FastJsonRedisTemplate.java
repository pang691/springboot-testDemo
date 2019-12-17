package com.taikang.health.iams.util;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class FastJsonRedisTemplate extends RedisTemplate<String, Object> {
    public FastJsonRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        FastJsonRedisSerializer redisSerializer = new FastJsonRedisSerializer();
        this.setKeySerializer(stringSerializer);
        this.setValueSerializer(redisSerializer);
        this.setHashKeySerializer(stringSerializer);
        this.setHashValueSerializer(redisSerializer);
    }

    public FastJsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}