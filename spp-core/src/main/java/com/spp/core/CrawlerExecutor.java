package com.spp.core;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spp.common.utils.*;
import com.spp.common.enums.*;
import com.spp.core.enums.CityNameParserEnum;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.enums.IpValueParserEnum;
import com.spp.core.enums.PortValueParserEnum;
import com.spp.core.pojo.ProxyIp;
import com.spp.core.pojo.Crawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


/**
 * @author zhougy
 * @date 2024/05/14
 */
public class CrawlerExecutor {
    private static final Logger log = LoggerFactory.getLogger(CrawlerExecutor.class);

    private ProxyIpPool proxyIpPool;

    private Lockable lockable;


    public void setProxyIpPool(ProxyIpPool proxyIpPool) {
        this.proxyIpPool = proxyIpPool;
    }

    public void setLockable(Lockable lockable) {
        this.lockable = lockable;
    }

    ExecutorService executorService = new ThreadPoolExecutor(
            5,
            200,
            60L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 运行所有爬虫
     */
    public void executeAllToPool() {
        List<ProxyIp> ipList = executeAll();
        // 放入ip池
        if (ipList != null && ipList.size() > 0) {
            if(proxyIpPool == null) {
                log.warn("no proxyIpPool found");
                return;
            }
            // 缓存
            ipList.forEach(proxyIp -> proxyIpPool.put(proxyIp));
        }
    }

    /**
     * 运行所有爬虫
     */
    public List<ProxyIp> executeAll() {
        List<ProxyIp> ipList = new ArrayList<>();
        List<Future<List<ProxyIp>>> futures = new ArrayList<>();
        List<Crawler> crawlers = CrawlerManager.getInstance().getCrawlers();
        if (crawlers == null || crawlers.isEmpty()) {
            log.warn("no crawler found");
            return ipList;
        }
        crawlers.forEach((context) -> {
            // 提交一个任务
            Future<List<ProxyIp>> future = executorService.submit(() -> executeOne(context));
            // 添加到future列表
            futures.add(future);
        });

        // 等待所有任务完成
        futures.forEach(future -> {
            try {
                List<ProxyIp> crawlerIpList = future.get();
                if (ipList != null && ipList.size() > 0) {
                    ipList.addAll(crawlerIpList);
                }
            } catch (Exception e) {
                log.error("crawler execute error：{}", e);
            }
        });
        return ipList;
    }

    /**
     * 运行单个爬虫
     *
     * @param key
     */
    public void executeToPool(String key) {
        List<ProxyIp> ipList = executeOne(key);

        // 放入ip池
        if (ipList != null && ipList.size() > 0) {
            // 缓存
            ipList.forEach(proxyIp -> proxyIpPool.put(proxyIp));
        }
    }


    /**
     * 运行单个爬虫
     *
     * @param key
     */
    public List<ProxyIp> executeOne(String key) {
        List<ProxyIp> ipList = new ArrayList<>();
        Crawler crawler = CrawlerManager.getInstance().getCrawler(key);
        if (crawler == null) {
            log.warn("no crawler found");
            return ipList;
        }
        // 执行
        return executeOne(crawler);
    }

    /**
     * 运行爬虫
     */
    private List<ProxyIp> executeOne(Crawler crawler) {
        boolean enable = crawler.isEnable();
        String key = crawler.getKey();
        String name = crawler.getName();
        List<ProxyIp> ipList = new ArrayList<>();
        if (!enable) {
            log.info("crawler:{} is disable", name);
            return ipList;
        }

        // 读取禁用列表
        List<String> disableList = CrawlerManager.getInstance().getDisableList();
        if (disableList!= null && disableList.contains(key)) {
            log.info("crawler:{} is disable", name);
            return ipList;
        }

        // 上下文
        CrawlerHolder.set(crawler);
        // 加锁
        boolean lock = crawler.isLock();
        if (lock) {
            if(lockable == null) {
                log.warn("no lockable found");
                return ipList;
            }
            if (lockable.isExist(key)) {
                log.info("crawler:{} is running >>>", name);
                return ipList;
            }
            lockable.lock(key);
        }
        try {
            // 执行
            ipList = executeOne();
        } catch (Exception e) {
            log.error("crawler:" + name + " execute is fail", e);
        } finally {
            // 解锁
            if (lock) {
                lockable.unlock(key);
            }
        }

        log.info("crawler:{}, execute is success, ip count:{}", name, ipList.size());
        return ipList;
    }


    /**
     * 爬虫执行
     */
    private List<ProxyIp> executeOne() {
        List<ProxyIp> ipList = new ArrayList<>();

        // 上下文
        Crawler crawler = CrawlerHolder.get();
        String name = crawler.getName();
        IpCrawlerTypeEnum type = crawler.getType();
        IpValueParserEnum ipValueParser = crawler.getIpValueParser();
        PortValueParserEnum portValueParser = crawler.getPortValueParser();
        CityNameParserEnum cityNameParser = crawler.getCityNameParser();
        DateField expireUnit = crawler.getExpireUnit();
        int expireOffset = crawler.getExpireOffset();

        // 设置要爬取的总页数
        int pages = crawler.getPages();

        String pageNoDictStr = crawler.getPageNoDict();
        JSONObject pageNoDictJson = StringUtils.isEmpty(pageNoDictStr) ? null : JSONObject.parseObject(pageNoDictStr);

        // 基础 URL
        String baseUrl = crawler.getBaseUrl();
        for (int page = 1; page <= pages; page++) {
            // 获取页码
            String pageNo = pageNoDictJson == null ? String.valueOf(page) : pageNoDictJson.getString(String.valueOf(page));
            String url = String.format(baseUrl, pageNo);
            try {

                switch (type) {
                    case HTML:
                        ipList.addAll(executeHtml(url));
                        break;
                    case JSON:
                        ipList.addAll(executeJson(url));
                        break;
                }

                // 解析 & 验证
                ipList = ipList.stream()
                        .map(proxyIp -> {
                            // 判断是否可用
                            if (!NetUtil.isOpen(proxyIp.getIp(), Integer.valueOf(proxyIp.getPort()), 1000)) {
                                return null;
                            }
                            // 解析ip
                            String ip = ipValueParser.parse(proxyIp.getIp());

                            // 解析port
                            String port = portValueParser.parse(proxyIp.getPort() + "");

                            // 解析城市
                            String city = cityNameParser.parse(proxyIp.getCity());
                            if (StringUtils.isEmpty(city)) {
                                return null;
                            }

                            // 过期时间
                            Date expireTime = DateUtil.offset(expireUnit, expireOffset);

                            Integer portInt = Integer.valueOf(port);
                            return buildProxyIp(city, ip, portInt, expireTime);
                        })
                        .filter(ObjectUtil::isNotEmpty)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                log.error("execute「{}」{}, fail：{}", name, url, e);
            }

            // 随机延迟5秒内执行
            int random = (int) (Math.random() * 5);
            try {
                Thread.sleep(random * 1000);
            } catch (InterruptedException e) {
                log.error("execute「{}」delay:{}s error:{}", name, random, e);
            }
        }

        return ipList;
    }

    /**
     * html网页
     *
     * @param url
     * @return
     * @throws IOException
     */
    private List<ProxyIp> executeHtml(String url) throws IOException {
        List<ProxyIp> ipList = new ArrayList<>();

        // 上下文
        Crawler crawler = CrawlerHolder.get();
        IpValueParserEnum ipValueParser = crawler.getIpValueParser();
        PortValueParserEnum portValueParser = crawler.getPortValueParser();

        log.info("execute html start「{}」{}", crawler.getName(), url);
        Connection connect = Jsoup.connect(url);
        Document doc = connect.get();

        Elements rows = doc.select(crawler.getRowsSelector());
        for (int i = 0; i < rows.size(); i++) {
            if (i == crawler.getHeadRowIndex()) {
                continue;
            }
            Element row = rows.get(i);

            Elements cells = row.select(crawler.getCellSelector());

            int size = cells.size();
            if (size <= 0) {
                continue;
            }

            String ip = null;
            // 存在ip列
            if (crawler.getIpIndex() <= (size - 1)) {
                if (ipValueParser.equals(IpValueParserEnum.DEFAULT)) {
                    ip = cells.get(crawler.getIpIndex()).text();
                } else {
                    ip = cells.get(crawler.getIpIndex()).html();
                }
            }

            // 存在端口列
            String port = null;
            if (crawler.getPortIndex() <= (size - 1)) {
                if (portValueParser.equals(PortValueParserEnum.DEFAULT)) {
                    port = cells.get(crawler.getPortIndex()).text();
                } else {
                    port = cells.get(crawler.getPortIndex()).html();
                }
            }

            // 存在城市列
            String city = null;
            if (crawler.getCityIndex() <= (size - 1)) {
                city = cells.get(crawler.getCityIndex()).text();
            }

            // 无效的行
            if (ip == null || port == null || city == null) {
                continue;
            }

            // 保存
            Integer portInt = Integer.valueOf(port);
            ipList.add(buildProxyIp(city, ip, portInt));
        }
        log.info("execute html end「{}」{}", crawler.getName(), url);
        return ipList;
    }

    /**
     * json接口
     *
     * @param url
     * @return
     */
    private List<ProxyIp> executeJson(String url) {
        List<ProxyIp> ipList = new ArrayList<>();

        // 上下文
        Crawler crawler = CrawlerHolder.get();

        log.info("execute json start「{}」{}", crawler.getName(), url);
        String body = HttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(body);

        JSONArray jsonArray = jsonObject.getJSONArray(crawler.getRowsParser());
        for (Object obj : jsonArray) {

            JSONObject item = (JSONObject) obj;
            String ip = item.getString(crawler.getIpParser());
            String port = item.getString(crawler.getPortParser());
            String city = item.getString(crawler.getCityParser());

            // 保存
            Integer portInt = Integer.valueOf(port);
            ipList.add(buildProxyIp(city, ip, portInt));
        }
        log.info("execute json start「{}」{}", crawler.getName(), url);
        return ipList;
    }

    private ProxyIp buildProxyIp(String city, String ip, Integer port) {
        ProxyIp proxyIp = new ProxyIp();
        proxyIp.setCity(city);
        proxyIp.setIp(ip);
        proxyIp.setPort(port);
        return proxyIp;
    }

    private ProxyIp buildProxyIp(String city, String ip, Integer port, Date expireTime) {
        ProxyIp proxyIp = new ProxyIp();
        proxyIp.setCity(city);
        proxyIp.setIp(ip);
        proxyIp.setPort(port);
        proxyIp.setExpireTime(expireTime);
        return proxyIp;
    }
}
