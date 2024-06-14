package com.spp.core.strategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class CityValueCnStyle2Parser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // 1-中国 北京、2-中国 广东 深圳
        String[] split = plainText.split(" ");
        String city = split[split.length - 1];
        return city;
    }
}
