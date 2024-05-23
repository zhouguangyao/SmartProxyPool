package com.spp.crawler;


import com.spp.core.ProxyIpPool;
import com.spp.core.pojo.ProxyIp;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 代理ip池（Custom）
 * @author zhougy
 * @date 2024/05/14
 */
@Component
public class ProxyIpCustomPool implements ProxyIpPool {

    private static CopyOnWriteArrayList<ProxyIp> memPool = new CopyOnWriteArrayList();

    @Override
    public void put(ProxyIp proxyIp) {
        memPool.add(proxyIp);
    }

    @Override
    public void remove(ProxyIp proxyIp) {
        memPool.remove(proxyIp);
    }

    /**
     * 获取随机
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp get() {
        if (memPool.isEmpty()){
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * memPool.size());
        ProxyIp proxyIp = memPool.get(randomIndex);
        if (proxyIp.getExpireTime().before(new Date())){
            memPool.remove(randomIndex);
            return get();
        }
        return proxyIp;
    }

    /**
     * 获取指定城市的随机
     * @param city
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp getByCity(String city) {
        if (memPool.isEmpty()){
            return null;
        }
        // filter by city
        List<ProxyIp> filteredList = memPool.stream().filter(proxyIp -> proxyIp.getCity().equals(city)).collect(Collectors.toList());
        if (filteredList.isEmpty()){
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * filteredList.size());
        ProxyIp proxyIp = filteredList.get(randomIndex);
        if (proxyIp.getExpireTime().before(new Date())){
            memPool.remove(randomIndex);
            return get();
        }
        return proxyIp;
    }
}
