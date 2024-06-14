package com.spp.core.strategy;

/**
 * 解析内容的策略
 *
 * @author zhougy
 * @date 2024/6/13 18:53
 */
public abstract class AbstractParserStrategy {
    /**
     *  解析内容
     * @param plainText
     * @return
     */
    abstract public String parse(String plainText);
}

