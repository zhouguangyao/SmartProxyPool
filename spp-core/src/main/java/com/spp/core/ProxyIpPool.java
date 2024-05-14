package com.spp.core;



import com.spp.core.pojo.ProxyIp;


/**
 * 代理ip池
 * @author zhougy
 * @date 2024/05/14
 */
public interface ProxyIpPool {

    /**
     * 增加
     * @param proxyIp
     */
    void put(ProxyIp proxyIp);

    /**
     * 移除
     * @param proxyIp
     */
    void remove(ProxyIp proxyIp);

    /**
     * 获取随机
     * @return {@link ProxyIp}
     */
    ProxyIp get();

    /**
     * 获取指定城市的随机
     * @param city
     * @return {@link ProxyIp}
     */
    ProxyIp getByCity(String city);
}
