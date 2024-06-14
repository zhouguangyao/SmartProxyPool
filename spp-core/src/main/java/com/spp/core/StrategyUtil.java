package com.spp.core;

import com.spp.core.strategy.AbstractParserStrategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:43
 */
public class StrategyUtil {

    public static String parse(Class<? extends AbstractParserStrategy> clazz, String plainText) {
        AbstractParserStrategy strategy = null;
        try {
            strategy = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (strategy == null) {
            return plainText;
        }
        return strategy.parse(plainText);
    }
}
