package com.spp.config;

import com.spp.core.CrawlerManager;
import com.spp.core.pojo.Crawler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/5/22 17:23
 */
@Component
public class LoadConfigCrawlerRunner implements CommandLineRunner {

    @Resource
    private SppConfig sppConfig;

    @Override
    public void run(String... args) throws Exception {
        // 加载配置的爬虫
        List<Crawler> crawlers = sppConfig.getCrawlers();
        if (crawlers != null && crawlers.size() > 0) {
            for (Crawler crawler : crawlers) {
                CrawlerManager.getInstance().addCrawler(crawler);
            }
        }

        // 加载禁用爬虫
        List<String> disableList = sppConfig.getDisableList();
        CrawlerManager.getInstance().setDisableList(disableList);
    }
}
