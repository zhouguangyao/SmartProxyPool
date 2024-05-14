package com.spp.core;


/**
 * 锁接口
 * @author zhougy
 * @date 2024/05/14
 */
public interface Lockable {

    /**
     * 锁是否存在
     * @param key
     * @return {@link String}
     */
    Boolean isExist(String key);

    /**
     * 上锁
     * @param key
     */
    void lock(String key);

    /**
     * 解锁
     * @param key
     */
    void unlock(String key);
}

