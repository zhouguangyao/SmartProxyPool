package com.spp.core;


import com.spp.core.pojo.ProxyIp;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 代理ip池（内存）
 * @author zhougy
 * @date 2024/05/14
 */
public class ProxyIpMemPool implements ProxyIpPool{

    private static CopyOnWriteArrayList<ProxyIp> memPool = new CopyOnWriteArrayList();

    @Override
    public void put(ProxyIp proxyIp) {
        memPool.add(proxyIp);
    }

    @Override
    public void remove(ProxyIp proxyIp) {
        memPool.remove(proxyIp);
    }

    @Override
    public ProxyIp get() {
        int randomIndex = (int) (Math.random() * memPool.size());
        ProxyIp proxyIp = memPool.get(randomIndex);
        if (proxyIp.getExpireTime().before(new Date())){
            return get();
        }
        return proxyIp;
    }

    @Override
    public ProxyIp getByCity(String city) {
        ProxyIp result = null;
        for (ProxyIp proxyIp : memPool) {
            if (proxyIp.getCity().equals(city)) {
                // 已过期
                if (proxyIp.getExpireTime().before(new Date())){
                    continue;
                }
                result = proxyIp;
                break;
            }
        }
        return result;
    }
}
