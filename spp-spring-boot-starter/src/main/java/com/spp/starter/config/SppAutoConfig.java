package com.spp.starter.config;


import com.spp.starter.utils.SpringUtil;
import com.spp.core.Lockable;
import com.spp.core.MemLock;
import com.spp.core.ProxyIpMemPool;
import com.spp.core.ProxyIpPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * spp-spring-boot-starter自动配置类
 * @author zhougy
 * @date 2024/05/14
 */
@Configuration
@Import(SpringUtil.class)
public class SppAutoConfig {

    private static final Logger log = LoggerFactory.getLogger(SppAutoConfig.class);

    @Bean
    public ProxyIpPool getProxyIpPool() {
//        ProxyIpPool pool = SpringUtil.getBean(ProxyIpPool.class);
//        // 如果没有配置ProxyIpPool，则默认使用ProxyIpMemPool
//        if (pool == null) {
//            pool = new ProxyIpMemPool();
//        }

        ProxyIpPool pool = new ProxyIpMemPool();
        return pool;
    }

    @Bean
    public Lockable getLockable() {
//        Lockable lockable = SpringUtil.getBean(Lockable.class);
//        // 如果没有配置Lockable，则默认使用MemLock
//        if (lockable == null) {
//            lockable = new MemLock();
//        }
        Lockable lockable = new MemLock();
        return lockable;
    }

}
