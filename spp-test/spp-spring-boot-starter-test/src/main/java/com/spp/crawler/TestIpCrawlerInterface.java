package com.spp.crawler;


import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.core.enums.CityNameParserEnum;


/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/2/26 16:29
 */
@ProxyIpCrawler
public interface TestIpCrawlerInterface {

    /**
     * 快代理
     */
    @ProxyIpCrawler(key = "kuaidaili-2", name = "快代理",
            baseUrl = "https://www.kuaidaili.com/free/inha/%s/", pages = 10,
            rowsSelector = "table tbody tr", cellSelector = "td",
            headRowIndex = -1, ipIndex = 0, portIndex = 1, cityIndex = 5, cityNameParser = CityNameParserEnum.CN_STYLE1,
            lock = true)
    public void kuaidaili();
}

