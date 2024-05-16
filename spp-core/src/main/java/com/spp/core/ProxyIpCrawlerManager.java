package com.spp.core;


import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.core.pojo.ProxyIpCrawlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhougy
 * @date 2024/05/14
 */
public class ProxyIpCrawlerManager {
    private static final Logger log = LoggerFactory.getLogger(ProxyIpCrawlerManager.class);

    private static ProxyIpCrawlerManager instance;

    private static Map<String, ProxyIpCrawlerContext> crawlerMap = new HashMap<>();


    public static ProxyIpCrawlerManager getInstance() {
        if (instance == null) {
            instance = new ProxyIpCrawlerManager();
        }
        return instance;
    }


    public void addCrawler(Object bean,Method method) {
        if (method.isAnnotationPresent(ProxyIpCrawler.class)) {
            ProxyIpCrawler annotation = method.getAnnotation(ProxyIpCrawler.class);
            String key = annotation.key();
            String name = annotation.name();
            boolean lock = annotation.lock();
            String beanName = bean.getClass().getName();
            if (key == null || "".equals(key)) {
                key = beanName + "." + method.getName();
            }
            // Store the method in the map
            crawlerMap.put(key, new ProxyIpCrawlerContext(bean, method));
            log.info("LOAD crawler>>>[{}-{}]-{}, lock:{}", key, name, beanName + "." + method.getName(), lock);
        }
    }

    public ProxyIpCrawlerContext getCrawler(String key) {
        return crawlerMap.get(key);
    }

    public List<ProxyIpCrawlerContext> getCrawlers() {
        return new ArrayList<>(crawlerMap.values());
    }



}
