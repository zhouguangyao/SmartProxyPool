package com.spp.core;


import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存锁
 * @author zhougy
 * @date 2024/05/14
 */
public class MemLock implements Lockable {

    private static final String MEM_LOCK = "MEM_LOCK_%s";


    private static ConcurrentHashMap<String, Integer> lockMap = new ConcurrentHashMap();

    /**
     * 锁是否存在
     * @param key
     * @return {@link String}
     */
    @Override
    public Boolean isExist(String key) {
        String lockKey = String.format(MEM_LOCK, key);
        Object lock = lockMap.get(lockKey);
        if (lock != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 上锁
     * @param key
     */
    @Override
    public void lock(String key){
        String lockKey = String.format(MEM_LOCK, key);
        Object lock = lockMap.get(lockKey);
        if (lock != null) {
            return;
        }
        // 加锁
        lockMap.put(lockKey, 1);
    }

    /**
     * 解锁
     * @param key
     */
    @Override
    public void unlock(String key){
        // 解锁
        String lockKey = String.format(MEM_LOCK, key);
        lockMap.remove(lockKey);
    }
}

