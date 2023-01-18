package com.example.func.local.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lichuang
 * @date 2022/03/25
 */
@Slf4j
@Component
public class RedisComponent<T> {

    /**
     * 尽量使用字符串缓存 简单高效 不需要序列化反序列化
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    /**
     * 永不过期
     */
    public void set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 过期时间自定义
     */
    public void set(String key, String value, Long timeout) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 过期时间自定义
     */
    public void set(String key, String value, Long timeout, TimeUnit unit) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
        }
    }

    public String get(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (SerializationException e) {
            error(key, e.getMessage());
            return null;
        } catch (Exception e) {
            error(key, e.getMessage());
            return stringRedisTemplate.opsForValue().get(key);
        }
    }

    /**
     * 永不过期
     */
    public void setObj(String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 过期时间自定义
     */
    public void setObj(String key, T value, Long timeout) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 过期时间自定义
     */
    public void setObj(String key, T value, Long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            error(key, value, e.getMessage());
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        }
    }

    public T getObj(String key) {
        try {
            ValueOperations<String, T> valueOps = redisTemplate.opsForValue();
            return valueOps.get(key);
        } catch (SerializationException e) {
            error(key, e.getMessage());
            return null;
        } catch (Exception e) {
            error(key, e.getMessage());
            ValueOperations<String, T> valueOps = redisTemplate.opsForValue();
            return valueOps.get(key);
        }
    }

    public Boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    private void error(String key, Object value, String message) {
        log.error("Redis set key:{},value:{} failed.", key, value);
        log.error("Error message:{}.", message);
    }

    private void error(String key, String message) {
        log.error("Redis get key:{} failed.", key);
        log.error("Error message:{}.", message);
    }
}
