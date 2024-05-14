package com.spp.starter.config;

import com.spp.core.ProxyIpCrawlerManager;
import com.spp.core.annotation.ProxyIpCrawler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/3/7 15:09
 */

@Component
public class CrawlerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(ProxyIpCrawler.class)) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(ProxyIpCrawler.class)) {
                    ProxyIpCrawlerManager.getInstance().addCrawler(bean, method);
                }
            }
        }
        return bean;
    }
}
