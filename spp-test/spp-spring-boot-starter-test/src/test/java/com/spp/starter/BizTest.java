package com.spp.starter;


import com.spp.core.ProxyIpCrawlerExecutor;
import com.spp.crawler.IpCrawler;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 *
 */
@SpringBootTest(classes = {com.spp.Application.class})
class BizTest {

    @Resource
    private IpCrawler ipCrawler;

    @Resource
    private ProxyIpCrawlerExecutor proxyIpCrawlerExecutor;

    @Test
    void runCrawler() {
//        proxyIpCrawlerManager.run("free-proxy");
//        proxyIpCrawlerManager.run("89ip");
        proxyIpCrawlerExecutor.execute("zdaye");
    }
}