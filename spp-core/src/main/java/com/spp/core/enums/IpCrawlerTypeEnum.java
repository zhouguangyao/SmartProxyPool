package com.spp.core.enums;

/**
 * 爬虫类型
 *
 * @author zhougy
 * @date 2022/6/20 14:25
 */
public enum IpCrawlerTypeEnum {

    HTML,
    JSON;


    public static IpCrawlerTypeEnum parse(String name) {
        // 尝试根据城市名获取枚举，忽略大小写
        for (IpCrawlerTypeEnum enum1 : values()) {
            if (enum1.name().equalsIgnoreCase(name)) {
                return enum1;
            }
        }
        // 如果没有匹配的枚举，使用默认策略
        return HTML;
    }
}

