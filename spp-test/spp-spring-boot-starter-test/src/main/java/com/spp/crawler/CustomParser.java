package com.spp.crawler;

import com.spp.core.strategy.AbstractParserStrategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class CustomParser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // do something to parse plainText
        return plainText;
    }
}
