package com.spp.config;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/2/23 15:43
 */

import com.spp.core.pojo.Crawler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "spp")
public class SppConfig {


    /**
     * 禁用
     */
    private List<String> disableList;


    /**
     * ip抓取器
     */
    private List<Crawler> crawlers;


    public List<String> getDisableList() {
        return disableList;
    }

    public void setDisableList(List<String> disableList) {
        this.disableList = disableList;
    }

    public List<Crawler> getCrawlers() {
        return crawlers;
    }

    public void setCrawlers(List<Crawler> crawlers) {
        this.crawlers = crawlers;
    }
}
