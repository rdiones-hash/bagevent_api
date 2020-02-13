package com.rdiones.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存 (String)
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存 (String)
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 (String)
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean set(final String key, Object value, Long expireTime,int type) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 (List)单个值写入可设置缓存时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean rightPush(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            operations.rightPush(key, value);
            //设置缓存时间
            if(expireTime > 0){
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 (List)批量写入
     *
     * @param key
     * @param values
     * @param expireTime
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean rightPushAll(final String key, Long expireTime, List<Object> values) {
        boolean result = false;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            for(Object value:values){
                operations.rightPush(key, value);
            }
            if(expireTime > 0){
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存 (List)（全部）
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> rangeAll(final String key) {
        List<Object> result = new ArrayList<Object>();
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            result = operations.range(key,0,-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存 (List)
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> range(final String key,long start,long end) {
        List<Object> result = new ArrayList<Object>();
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            result = operations.range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除list中的value (List)
     *
     * @param key
     * @param count
     *          count > 0：删除等于从头到尾移动的值的元素。
    count < 0：删除等于从尾到头移动的值的元素。
    count = 0：删除等于value的所有元素。
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public void removeListValue(final String key,long count,String value) {
        try {
            if(exists(key)){
                ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
                operations.remove(key,count,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
