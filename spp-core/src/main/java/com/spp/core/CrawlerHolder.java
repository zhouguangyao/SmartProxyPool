package com.spp.core;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.spp.core.pojo.Crawler;

/**
 * @author zhougy
 * @date 2024/05/14
 */
public class CrawlerHolder {
    private static final ThreadLocal<Crawler> holder = new TransmittableThreadLocal();

    public CrawlerHolder() {
    }

    public static void reset() {
        holder.remove();
    }

    public static void set(Crawler item) {
        if (item == null) {
            reset();
        } else {
            holder.set(item);
        }
    }

    public static Crawler get() {
        return holder.get();
    }
}

