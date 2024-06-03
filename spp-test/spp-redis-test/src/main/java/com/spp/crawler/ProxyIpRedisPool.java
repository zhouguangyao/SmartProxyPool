package com.spp.crawler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spp.common.utils.CollUtil;
import com.spp.core.ProxyIpPool;
import com.spp.core.pojo.ProxyIp;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 代理ip池（Redis）
 *
 * @author zhougy
 * @date 2024/06/03
 */
@Component
public class ProxyIpRedisPool implements ProxyIpPool {


    private static final String CACHE_KEY_PREFIX = "proxy_ip";

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void put(ProxyIp proxyIp) {
        String cacheKey = CACHE_KEY_PREFIX + ":" + proxyIp.getCity() + ":" + proxyIp.getIp();
        long expireMillis = proxyIp.getExpireTime().getTime() - System.currentTimeMillis();
        if (expireMillis <= 0) {
            return;
        }
        redisTemplate.boundValueOps(cacheKey).set(JSON.toJSONString(proxyIp), expireMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void remove(ProxyIp proxyIp) {
        String cacheKey = CACHE_KEY_PREFIX + ":" + proxyIp.getCity() + ":" + proxyIp.getIp();
        redisTemplate.delete(cacheKey);
    }

    /**
     * 获取随机
     *
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp get() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (CollUtil.isEmpty(keys)) {
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * keys.size());
        String key = new ArrayList<>(keys).get(randomIndex);
        Object object = redisTemplate.boundValueOps(key).get();
        if (object == null) {
            return null;
        }
        return JSONObject.parseObject(object.toString(), ProxyIp.class);
    }

    /**
     * 获取指定城市的随机
     *
     * @param city
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp getByCity(String city) {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + ":" + city + "*");
        if (CollUtil.isEmpty(keys)) {
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * keys.size());
        String key = new ArrayList<>(keys).get(randomIndex);
        Object object = redisTemplate.boundValueOps(key).get();
        if (object == null) {
            return null;
        }
        return JSONObject.parseObject(object.toString(), ProxyIp.class);
    }
}
