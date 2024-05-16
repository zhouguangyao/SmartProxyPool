package com.spp.config;


import com.spp.utils.SpringUtil;
import com.spp.core.Lockable;
import com.spp.core.MemLock;
import com.spp.core.ProxyIpMemPool;
import com.spp.core.ProxyIpPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @ConditionalOnMissingBean(ProxyIpPool.class)
    public ProxyIpPool getProxyIpPool() {
        return new ProxyIpMemPool();
    }

    @Bean
    @ConditionalOnMissingBean(Lockable.class)
    public Lockable getLockable() {
        return new MemLock();
    }

}
