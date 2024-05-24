package com.spp.config;

import com.spp.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author zhougy
 * @date 2024/05/22
 */
@Component
public class SppSpringExecutor extends CrawlerExecutor {
    @Resource
    private ProxyIpPool proxyIpPool;

    @Resource
    private Lockable lockable;

    public SppSpringExecutor() {
        setProxyIpPool(proxyIpPool);
        setLockable(lockable);
    }
}
