package com.spp.core;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.spp.core.pojo.ProxyIpCrawlerContext;

/**
 * @author zhougy
 * @date 2024/05/14
 */
public class ProxyIpCrawlerContextHolder {
    private static final ThreadLocal<ProxyIpCrawlerContext> holder = new TransmittableThreadLocal();

    public ProxyIpCrawlerContextHolder() {
    }

    public static void reset() {
        holder.remove();
    }

    public static void set(ProxyIpCrawlerContext item) {
        if (item == null) {
            reset();
        } else {
            holder.set(item);
        }
    }

    public static ProxyIpCrawlerContext get() {
        return holder.get();
    }
}

