package com.spp.config;

import cn.hutool.core.util.ClassUtil;
import com.spp.core.CrawlerManager;
import com.spp.core.annotation.ProxyIpCrawler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 〈功能概述〉
 *
 * @author zhougy
 * @date 2024/06/12
 */

@Component
public class CrawlerInterfacePostProcessor implements BeanDefinitionRegistryPostProcessor {

    public void run(BeanDefinitionRegistry registry) {
        Set<Class<?>> interfaces = ClassUtil.scanPackageByAnnotation("", ProxyIpCrawler.class);

        for (Class<?> cls : interfaces) {
            if (cls.isAnnotationPresent(ProxyIpCrawler.class)) {
                for (Method method : cls.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(ProxyIpCrawler.class)) {
                        CrawlerManager.getInstance().addAnnotationCrawler(cls, method);
                    }
                }
            }

        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        run(registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
