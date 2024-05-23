## 一、项目介绍

🎉便捷的ip代理池。代码简洁、无外部依赖。

1. 内置多个代理ip数据源，具体可查看DefaultIpCrawler类。
2. 支持自定义注解类指定数据源
3. 支持配置文件指定数据源
4. 支持自定义代理池实现，默认使用内存代理池
5. 支持自定义锁实现，默认使用内存锁



## 二、Maven 引用方式

```xml
 <dependency>
     <groupId>io.github.zhouguangyao</groupId>
     <artifactId>spp-spring-boot-starter</artifactId>
     <version>${spp.version}</version>
 </dependency>
```

## 三、配置示例
配置文件配置、注解类配置、两种方式都可以进行配置。
### 配置文件配置
包含html和json两种类型爬虫，可以根据需求选择。
``` yml

spp:
  #########################################
  # crawlers configuration
  #########################################
  crawlers:
    - key: htmlCrawler1                     # html爬虫唯一标识
      enable: true                          # 是否启用，默认true
      name: html示例爬虫1                    # 爬虫名称
      baseUrl: http://www.example.com       # 爬虫URL
      pages: 1                              # 爬取页面数，默认1
      pageNoDict: "{1:\"b97827cc\", 2:\"f8a0e15f\"}"    # 爬取页码字典
      type: HTML                            # 爬取页面类型
      rowsSelector: div.table-responsive table tbody tr # 爬取数据行的选择器（ip列表）
      cellSelector: td                      # 爬取数据单元格的选择器（单个ip）
      headRowIndex: 0                       # 表头行索引，默认0
      ipIndex: 0                            # IP地址索引，默认0
      ipValueParser: DEFAULT                # IP地址解析方式，默认DEFAULT
      portIndex: 1                          # 端口索引，默认1
      portValueParser: DEFAULT              # 端口解析方式，默认DEFAULT
      cityIndex: 2                          # 城市索引，默认2
      cityNameParser: DEFAULT               # 城市名称解析方式，默认DEFAULT
      expireUnit: MINUTE                    # 过期时间单位，默认MINUTE
      expireOffset: 10                      # 过期时间偏移量，默认10
      lock: false                           # 是否启用锁，默认false   
    - key: jsonCrawler1                     # json爬虫唯一标识
      enable: true                          # 是否启用，默认true
      name: json示例爬虫1                     # 爬虫名称
      baseUrl: http://www.example.com       # 爬虫URL
      type: JSON                            # 爬取页面类型
      rowsParser: data                      # 解析json数据key
      cityNameParser: DEFAULT               # 城市名称解析方式，默认DEFAULT
      expireUnit: MINUTE                    # 过期时间单位，默认MINUTE
      expireOffset: 10                      # 过期时间偏移量，默认10
      lock: false                           # 是否启用锁，默认false
```


### 注解类配置
包含html和json两种类型爬虫，可以根据需求选择。
``` java
package com.spp.crawler;

import com.spp.core.annotation.ProxyIpCrawler;
import com.spp.core.enums.CityNameParserEnum;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.enums.IpValueParserEnum;
import org.springframework.stereotype.Component;

@Component
@ProxyIpCrawler
public class TestIpCrawler {

    /**
     * HTML-快代理
     */
    @ProxyIpCrawler(key = "kuaidaili", name = "快代理",
            baseUrl = "https://www.kuaidaili.com/free/inha/%s/", pages = 10,
            rowsSelector = "table tbody tr", cellSelector = "td",
            headRowIndex = -1, ipIndex = 0, portIndex = 1, cityIndex = 5, cityNameParser = CityNameParserEnum.CN_STYLE1,
            lock = true)
    public void kuaidaili() {
    }


    /**
     * JSON-geonode
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

}

```
### 配置自定义代理池
```java
package com.spp.crawler;

import com.spp.core.ProxyIpPool;
import com.spp.core.pojo.ProxyIp;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 代理ip池（DIY）
 * @author zhougy
 * @date 2024/05/14
 */
@Component
public class ProxyIpDiyPool implements ProxyIpPool {

    private static CopyOnWriteArrayList<ProxyIp> memPool = new CopyOnWriteArrayList();

    @Override
    public void put(ProxyIp proxyIp) {
        memPool.add(proxyIp);
    }

    @Override
    public void remove(ProxyIp proxyIp) {
        memPool.remove(proxyIp);
    }

    /**
     * 获取随机
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp get() {
        if (memPool.isEmpty()){
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * memPool.size());
        ProxyIp proxyIp = memPool.get(randomIndex);
        if (proxyIp.getExpireTime().before(new Date())){
            memPool.remove(randomIndex);
            return get();
        }
        return proxyIp;
    }

    /**
     * 获取指定城市的随机
     * @param city
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp getByCity(String city) {
        if (memPool.isEmpty()){
            return null;
        }
        // filter by city
        List<ProxyIp> filteredList = memPool.stream().filter(proxyIp -> proxyIp.getCity().equals(city)).collect(Collectors.toList());
        if (filteredList.isEmpty()){
            return null;
        }
        // random select
        int randomIndex = (int) (Math.random() * filteredList.size());
        ProxyIp proxyIp = filteredList.get(randomIndex);
        if (proxyIp.getExpireTime().before(new Date())){
            memPool.remove(randomIndex);
            return get();
        }
        return proxyIp;
    }
}


```

## 四、使用示例
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MainTest {

    @Resource
    private SppSpringExecutor executor;


    @Test
    public void runCrawler() {
        // 抓取数据源ip资源
        List<ProxyIp> ipList = executor.executeOne("kuaidaili");
    }

    @Resource
    private ProxyIpPool proxyIpPool;

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
```