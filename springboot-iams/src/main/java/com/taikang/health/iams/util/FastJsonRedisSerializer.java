package com.taikang.health.iams.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class FastJsonRedisSerializer implements RedisSerializer<Object> {
    public FastJsonRedisSerializer() {
    }

    public byte[] serialize(Object o) throws SerializationException {
        return o == null ? null : JSON.toJSONBytes(o, new SerializerFeature[]{SerializerFeature.WriteClassName, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat});
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : JSON.parse(bytes, new Feature[0]);
    }
}