package com.spp.core.strategy;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class DefaultParser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // 默认实现，如果没有匹配的枚举，返回原字符串
        return plainText;
    }
}
