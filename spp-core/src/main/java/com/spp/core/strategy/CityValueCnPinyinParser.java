package com.spp.core.strategy;

import com.spp.common.utils.CityUtils;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class CityValueCnPinyinParser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // Shenzhen
        String city = CityUtils.parseCityPinyin(plainText);
        return city;
    }
}
