package com.spp;


import com.alibaba.fastjson.JSON;
import com.spp.config.SppSpringExecutor;
import com.spp.core.ProxyIpPool;
import com.spp.core.pojo.ProxyIp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class CrawlerTest {

    @Resource
    private ProxyIpPool proxyIpPool;

    @Resource
    private SppSpringExecutor executor;


    /**
     * 抓取单个数据源
     */
    @Test
    public void runCrawler() {
        List<ProxyIp> ipList = executor.executeOne("zdaye");
        System.out.println(JSON.toJSONString(ipList));

    }


    /**
     * 抓取全部数据源
     */
    @Test
    public void runAllCrawler() {
        List<ProxyIp> ipList = executor.executeAll();
        System.out.println(JSON.toJSONString(ipList));

    }

    /**
     * 抓取多个数据源
     */
    @Test
    public void runManyCrawler() {
        List<String> siteList = Arrays.asList("kuaidaili", "free-proxy", "zdaye", "kxdaili", "89ip", "ip3366", "ihuan", "iproyal", "geonode");

        List<ProxyIp> ipList = new ArrayList();
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                200,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                new ThreadPoolExecutor.AbortPolicy());
        List<Future<List<ProxyIp>>> futures = new ArrayList<>();
        siteList.forEach(site -> {
            // 提交一个任务
            Future<List<ProxyIp>> future = executorService.submit(() ->
                executor.executeOne(site)
            );
            futures.add(future);
        });
        futures.forEach(future -> {
            try {
                List<ProxyIp> crawlerIpList = future.get();
                if (ipList != null && ipList.size() > 0) {
                    ipList.addAll(crawlerIpList);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println(JSON.toJSONString(ipList));
    }

    @Test
    public void getProxyIp() {
        // 获取随机的ip
        ProxyIp proxyIp = proxyIpPool.get();
        System.out.println(JSON.toJSONString(proxyIp));

        // 获取指定城市的ip
        ProxyIp cityProxyIp = proxyIpPool.getByCity("深圳市");
        System.out.println(JSON.toJSONString(cityProxyIp));

    }

}
