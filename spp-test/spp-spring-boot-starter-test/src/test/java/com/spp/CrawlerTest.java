package com.spp;


import com.spp.core.Lockable;
import com.spp.core.ProxyIpCrawlerExecutor;
import com.spp.core.ProxyIpPool;
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
    private Lockable lockable;


    @Test
    public void runCrawler() {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                200,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                new ThreadPoolExecutor.AbortPolicy());
        ProxyIpCrawlerExecutor proxyIpCrawlerExecutor = new ProxyIpCrawlerExecutor(proxyIpPool, lockable);


        // 执行多个站点
        List<String> siteList = Arrays.asList("kuaidaili", "free-proxy", "zdaye", "kxdaili", "89ip", "ip3366", "ihuan", "iproyal", "geonode");

        List<Future<?>> futures = new ArrayList<>();
        siteList.forEach(site -> {
            // 提交一个任务
            Future<?> future = executorService.submit(() -> {
                proxyIpCrawlerExecutor.execute(site);
            });
            futures.add(future);
        });
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 执行单个站点
//        proxyIpCrawlerExecutor.execute("zdaye");

    }

}
