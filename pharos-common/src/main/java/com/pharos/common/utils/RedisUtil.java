package com.pharos.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public String get(String key) {
        Object result = stringRedisTemplate.opsForValue().get(key);
        if (Objects.isNull(result)) {
            return null;
        }
        return StringUtils.isBlank(key) ? null : String.valueOf(result);
    }

    public boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean remove(String key) {
        try {
            stringRedisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean set(String key, String value, long time) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean expire(String key, long time) {
        try {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean increment(String key) {
        try {
            stringRedisTemplate.opsForValue().increment(key, 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
