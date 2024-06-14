package com.spp.core.strategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class CityValueCnStyle3Parser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        String city;
        // 阿里云电信 广东省深圳市南山区
        String[] parts = plainText.split(" ");
        if (parts.length > 1) {
            city = parts[0].substring(parts[0].indexOf("省") + 1);
        } else {
            city = parts[0];
        }
        // 去掉xx省
        city = city.substring(city.indexOf("省") + 1);
        // 去掉xx区
        city = city.substring(0, city.indexOf("市") + 1);
        return city;
    }
}
