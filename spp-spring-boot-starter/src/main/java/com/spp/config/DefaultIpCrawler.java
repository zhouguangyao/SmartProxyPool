package com.spp.config;


import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.core.enums.CityNameParserEnum;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.enums.IpValueParserEnum;
import org.springframework.stereotype.Component;


/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/2/26 16:29
 */
@Component
@ProxyIpCrawler
public class DefaultIpCrawler {

    /**
     * 快代理
     */
    @ProxyIpCrawler(key = "kuaidaili", name = "快代理",
            baseUrl = "https://www.kuaidaili.com/free/inha/%s/", pages = 10,
            rowsSelector = "table tbody tr", cellSelector = "td",
            headRowIndex = -1, ipIndex = 0, portIndex = 1, cityIndex = 5, cityNameParser = CityNameParserEnum.CN_STYLE1,
            lock = true)
    public void kuaidaili() {
    }

    /**
     * 站大爷
     */
    @ProxyIpCrawler(key = "zdaye", name = "站大爷",
            baseUrl = "https://www.zdaye.com/free/1/?sAdr=guangdong", pages = 1,
            rowsSelector = "table tr", cellSelector = "td",
            ipIndex = 0, portIndex = 1, cityIndex = 3, cityNameParser = CityNameParserEnum.CN_STYLE3)
    public void zdaye() {
    }

    /**
     * 开心代理
     */
    @ProxyIpCrawler(key = "kxdaili", name = "开心代理",
            baseUrl = "http://www.kxdaili.com/dailiip/1/%s.html", pages = 10,
            rowsSelector = "table tbody tr", cellSelector = "td",
            ipIndex = 0, portIndex = 1, cityIndex = 5, cityNameParser = CityNameParserEnum.CN_STYLE3,
            lock = true)
    public void kxdaili() {
    }

    /**
     * iproyal
     */
    @ProxyIpCrawler(key = "iproyal", name = "iproyal",
            enable = false,
            baseUrl = "https://iproyal.com/free-proxy-list/?page=%s&entries=100", pages = 1,
            rowsSelector = "div.overflow-auto.astro-lmapxigl div.card-mode-layout", cellSelector = ".flex.items-center",
            cityIndex = 4,
            lock = true)
    public void iproyal() {
    }


    /**
     * 刷新云代理免费代理
     */
    @ProxyIpCrawler(key = "ip3366", name = "云代理",
            baseUrl = "https://proxy.ip3366.net/free/?action=china&page=%s", pages = 10,
            rowsSelector = "div.container table tbody tr", cellSelector = "td",
            cityIndex = 4, cityNameParser = CityNameParserEnum.CN_STYLE2,
            lock = true)
    public void ip3366() {

    }


    /**
     * {
     * "_id": "6325466d2fb0f02dd5699cb8",
     * "ip": "119.91.35.77",
     * "anonymityLevel": "elite",
     * "asn": "AS45090",
     * "city": "Chaowai",
     * "country": "CN",
     * "created_at": "2022-09-17T04:00:45.562Z",
     * "google": false,
     * "isp": "China Internet Network Information Center",
     * "lastChecked": 1709779200,
     * "latency": 208.798,
     * "org": "Tencent cloud computing (Beijing) Co., Ltd.",
     * "port": "2080",
     * "protocols": [
     * "socks4"
     * ]
     */
    @ProxyIpCrawler(key = "geonode", name = "geonode",
            baseUrl = "https://proxylist.geonode.com/api/proxy-list?country=CN&anonymityLevel=elite&filterLastChecked=10&limit=500&page=1&sort_by=lastChecked&sort_type=desc",
            type = IpCrawlerTypeEnum.JSON,
            rowsParser = "data", cityNameParser = CityNameParserEnum.CN_PINYIN)
    public void geonode() {

    }

    @ProxyIpCrawler(key = "ihuan", name = "小幻代理",
            baseUrl = "https://ip.ihuan.me/address/5bm/5Lic.html?page=%s", pages = 10,
            pageNoDict = "{\"1\":\"b97827cc\", \"2\":\"4ce63706\", \"3\":\"5crfe930\", \"4\":\"f3k1d581\", \"5\":\"ce1d45977\", \"6\":\"881aaf7b5\", \"7\":\"eas7a436\", \"8\":\"5crfe930\", \"9\":\"2d28bd81a\", \"10\":\"a42g5985d\"}",
            rowsSelector = "div.table-responsive table tbody tr", cellSelector = "td",
            cityNameParser = CityNameParserEnum.CN_STYLE2,
            lock = true)
    public void ihuan() {

    }

    @ProxyIpCrawler(key = "89ip", name = "89代理",
            baseUrl = "https://www.89ip.cn/index_%s.html", pages = 100,
            rowsSelector = "div.layui-form table tbody tr", cellSelector = "td", headRowIndex = -1,
            cityNameParser = CityNameParserEnum.CN_STYLE3,
            lock = true)
    public void _89ip() {
    }

    @ProxyIpCrawler(key = "free-proxy", name = "新鲜代理",
            baseUrl = "http://free-proxy.cz/zh/proxylist/country/CN/all/ping/level1/%s", pages = 100,
            rowsSelector = "#proxy_list tbody tr", cellSelector = "td",
            ipValueParser = IpValueParserEnum.BASE64,
            cityIndex = 5, cityNameParser = CityNameParserEnum.CN_PINYIN,
            lock = true)
    public void freeProxy() {

    }

}

