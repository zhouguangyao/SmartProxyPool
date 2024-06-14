package com.spp.core.strategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class CityValueCnStyle1Parser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // 阿里云电信 深圳市
        String city = plainText.split(" ")[0];
        return city;
    }
}
