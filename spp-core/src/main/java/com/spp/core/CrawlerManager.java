package com.spp.core;


import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.core.pojo.Crawler;
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
public class CrawlerManager {
    private static final Logger log = LoggerFactory.getLogger(CrawlerManager.class);

    private static CrawlerManager instance;

    private static List<String> disableList = new ArrayList<>();

    private static Map<String, Crawler> crawlerMap = new HashMap<>();


    public static CrawlerManager getInstance() {
        if (instance == null) {
            instance = new CrawlerManager();
        }
        return instance;
    }


    /**
     * 添加注解爬虫
     * @param bean
     * @param method
     */
    public void addAnnotationCrawler(Object bean,Method method) {
        if (method.isAnnotationPresent(ProxyIpCrawler.class)) {
            ProxyIpCrawler annotation = method.getAnnotation(ProxyIpCrawler.class);
            String key = annotation.key();
            String name = annotation.name();
            boolean lock = annotation.lock();
            String beanName = bean.getClass().getName();
            if (key == null || "".equals(key)) {
                key = beanName + "." + method.getName();
            }
            // 注解转换为配置
            Crawler crawler = new Crawler();
            crawler.setValue(annotation.value());
            crawler.setEnable(annotation.enable());
            crawler.setKey(key);
            crawler.setName(name);
            crawler.setBaseUrl(annotation.baseUrl());
            crawler.setPages(annotation.pages());
            crawler.setPageNoDict(annotation.pageNoDict());
            crawler.setType(annotation.type());
            crawler.setRowsParser(annotation.rowsParser());
            crawler.setIpParser(annotation.ipParser());
            crawler.setPortParser(annotation.portParser());
            crawler.setCityParser(annotation.cityParser());
            crawler.setRowsSelector(annotation.rowsSelector());
            crawler.setCellSelector(annotation.cellSelector());
            crawler.setHeadRowIndex(annotation.headRowIndex());
            crawler.setIpIndex(annotation.ipIndex());
            crawler.setIpValueParser(annotation.ipValueParser());
            crawler.setPortIndex(annotation.portIndex());
            crawler.setPortValueParser(annotation.portValueParser());
            crawler.setCityIndex(annotation.cityIndex());
            crawler.setCityNameParser(annotation.cityNameParser());
            crawler.setExpireUnit(annotation.expireUnit());
            crawler.setExpireOffset(annotation.expireOffset());
            crawler.setLock(lock);

            // 添加爬虫
            addCrawler(crawler);
        }
    }

    /**
     * 添加爬虫
     * @param crawler
     */
    public void addCrawler(Crawler crawler) {
        String key = crawler.getKey();
        String name = crawler.getName();
        boolean lock = crawler.isLock();
        // Store the method in the map
        crawlerMap.put(key, crawler);
        log.info("LOAD crawler>>>[{}-{}]-{}, lock:{}", key, name, lock);
    }

    public Crawler getCrawler(String key) {
        return crawlerMap.get(key);
    }

    public List<Crawler> getCrawlers() {
        return new ArrayList<>(crawlerMap.values());
    }

    public List<String> getDisableList() {
        return disableList;
    }

    public void setDisableList(List<String> disableList) {
        CrawlerManager.disableList = disableList;
    }
}
