package com.spp.core;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spp.common.utils.*;
import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.common.enums.*;
import com.spp.core.enums.CityNameParserEnum;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.enums.IpValueParserEnum;
import com.spp.core.enums.PortValueParserEnum;
import com.spp.core.pojo.ProxyIp;
import com.spp.core.pojo.ProxyIpCrawlerContext;
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
public class ProxyIpCrawlerExecutor {
    private static final Logger log = LoggerFactory.getLogger(ProxyIpCrawlerExecutor.class);

    private ProxyIpPool proxyIpPool;

    private Lockable lockable;

    ExecutorService executorService = new ThreadPoolExecutor(
            5,
            200,
            60L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 运行所有爬虫
     */
    public void executeAll() {
        List<Future> futures = new ArrayList<>();
        List<ProxyIpCrawlerContext> crawlers = ProxyIpCrawlerManager.getInstance().getCrawlers();
        crawlers.forEach((context) -> {
            // 提交一个任务
            Future<?> future = executorService.submit(() -> {
                execute(context);
            });
            // 添加到future列表
            futures.add(future);
        });

        // 等待所有任务完成
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                log.error("crawler execute error：{}", e.getMessage());
            }
        });
    }

    /**
     * 运行单个爬虫
     *
     * @param key
     */
    public void execute(String key) {
        ProxyIpCrawlerContext context = ProxyIpCrawlerManager.getInstance().getCrawler(key);
        // 提交一个任务
        Future<?> future = executorService.submit(() -> {
            execute(context);
        });


        // 等待任务完成
        try {
            future.get();
        } catch (Exception e) {
            log.error("crawler execute error：{}", e.getMessage());
        }
    }

    /**
     * 运行爬虫
     */
    public void execute(ProxyIpCrawlerContext context) {
        ProxyIpCrawler annotation = context.getAnnotation();
        boolean enable = annotation.enable();
        String key = annotation.key();
        String name = annotation.name();
        if (!enable) {
            log.info("crawler:{} is disable", name);
            return;
        }

        // 读取禁用列表， todo
        List<String> disableList = null;
        if (disableList!= null && disableList.contains(key)) {
            log.info("crawler:{} is disable", name);
            return;
        }

        // 上下文
        ProxyIpCrawlerContextHolder.set(context);
        // 加锁
        boolean lock = annotation.lock();
        if (lock) {
            if (lockable.isExist(key)) {
                log.info("crawler:{} is running >>>", name);
                return;
            }
            lockable.lock(key);
        }
        List<ProxyIp> ipList = new ArrayList<>();
        try {
            // 执行
            ipList = execute();
        } catch (Exception e) {
            log.error("crawler:" + name + " execute is fail", e);
        } finally {
            // 解锁
            if (lock) {
                lockable.unlock(key);
            }
        }

        if (ipList != null && ipList.size() > 0) {
            // 缓存
            ipList.forEach(proxyIp -> proxyIpPool.put(proxyIp));
        }

        log.info("crawler:{}, execute is success, ip count:{}", name, ipList.size());
    }


    /**
     * 爬虫执行
     */
    public List<ProxyIp> execute() {
        List<ProxyIp> ipList = new ArrayList<>();

        // 上下文
        ProxyIpCrawlerContext context = ProxyIpCrawlerContextHolder.get();
        ProxyIpCrawler annotation = context.getAnnotation();
        String name = annotation.name();
        IpCrawlerTypeEnum type = annotation.type();
        IpValueParserEnum ipValueParser = annotation.ipValueParser();
        PortValueParserEnum portValueParser = annotation.portValueParser();
        CityNameParserEnum cityNameParser = annotation.cityNameParser();
        DateField expireUnit = annotation.expireUnit();
        int expireOffset = annotation.expireOffset();

        // 设置要爬取的总页数
        int pages = annotation.pages();

        String pageNoDictStr = annotation.pageNoDict();
        JSONObject pageNoDictJson = StringUtils.isEmpty(pageNoDictStr) ? null : JSONObject.parseObject(pageNoDictStr);

        // 基础 URL
        String baseUrl = annotation.baseUrl();
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
            } catch (IOException e) {
                log.error("抓取「{}」{}, 失败：{}", name, url, e);
            }

            // 随机延迟5秒内执行
            int random = (int) (Math.random() * 5);
            try {
                Thread.sleep(random * 1000);
            } catch (InterruptedException e) {
                log.error("抓取「{}」延迟执行异常:{}", name, e);
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
        ProxyIpCrawlerContext context = ProxyIpCrawlerContextHolder.get();
        ProxyIpCrawler annotation = context.getAnnotation();
        IpValueParserEnum ipValueParser = annotation.ipValueParser();
        PortValueParserEnum portValueParser = annotation.portValueParser();

        Connection connect = Jsoup.connect(url);
        Document doc = connect.get();

        Elements rows = doc.select(annotation.rowsSelector());
        for (int i = 0; i < rows.size(); i++) {
            if (i == annotation.headRowIndex()) {
                continue;
            }
            Element row = rows.get(i);

            Elements cells = row.select(annotation.cellSelector());

            int size = cells.size();
            if (size <= 0) {
                continue;
            }

            String ip = null;
            // 存在ip列
            if (annotation.ipIndex() <= (size - 1)) {
                if (ipValueParser.equals(IpValueParserEnum.DEFAULT)) {
                    ip = cells.get(annotation.ipIndex()).text();
                } else {
                    ip = cells.get(annotation.ipIndex()).html();
                }
            }

            // 存在端口列
            String port = null;
            if (annotation.portIndex() <= (size - 1)) {
                if (portValueParser.equals(PortValueParserEnum.DEFAULT)) {
                    port = cells.get(annotation.portIndex()).text();
                } else {
                    port = cells.get(annotation.portIndex()).html();
                }
            }

            // 存在城市列
            String city = null;
            if (annotation.cityIndex() <= (size - 1)) {
                city = cells.get(annotation.cityIndex()).text();
            }

            // 无效的行
            if (ip == null || port == null || city == null) {
                continue;
            }

            // 保存
            Integer portInt = Integer.valueOf(port);
            ipList.add(buildProxyIp(city, ip, portInt));
        }
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
        ProxyIpCrawlerContext context = ProxyIpCrawlerContextHolder.get();
        ProxyIpCrawler annotation = context.getAnnotation();

        String body = HttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(body);

        JSONArray jsonArray = jsonObject.getJSONArray(annotation.rowsParser());
        for (Object obj : jsonArray) {

            JSONObject item = (JSONObject) obj;
            String ip = item.getString(annotation.ipParser());
            String port = item.getString(annotation.portParser());
            String city = item.getString(annotation.cityParser());

            // 保存
            Integer portInt = Integer.valueOf(port);
            ipList.add(buildProxyIp(city, ip, portInt));
        }
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
