package com.spp.config;


import com.spp.utils.SpringUtil;
import com.spp.core.Lockable;
import com.spp.core.MemLock;
import com.spp.core.ProxyIpMemPool;
import com.spp.core.ProxyIpPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * spp-spring-boot-starter自动配置类
 * <br>ProxyIpPool
 * <br>Lockable
 * <br>CrawlerBeanPostProcessor
 * <br>DefaultIpCrawler
 * <br>LoadConfigCrawlerRunner
 * <br>SppAutoConfig
 * <br>SppConfig
 * <br>SppSpringExecutor
 * @author zhougy
 * @date 2024/05/14
 */
@Configuration
@Import({SpringUtil.class, CrawlerBeanPostProcessor.class, DefaultIpCrawler.class, LoadConfigCrawlerRunner.class, SppConfig.class, SppSpringExecutor.class})
public class SppAutoConfig {

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
