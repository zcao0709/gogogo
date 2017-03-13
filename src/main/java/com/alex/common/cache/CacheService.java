package com.alex.common.cache;

import com.alex.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by caozhennan on 2017/3/12.
 */
@Component
public class CacheService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean valueSet(Class<?> clazz, String desc, String value) {
        String key = keyGenerator(clazz, desc);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public String valueGet(Class<?> clazz, String desc) {
        String key = keyGenerator(clazz, desc);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    private String keyGenerator(Class<?> clazz, String desc) {
        return Const.APP_PREFIX + "_" +  clazz.getSimpleName() + "_" + desc;
    }
}
