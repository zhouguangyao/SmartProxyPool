package com.spp.core.enums;

/**
 * 解析内容的策略接口
 * @author zhougy
 * @date 2022/6/20 14:25
 */
public interface ParserStrategy {
    /**
     *  解析内容
     * @param plainText
     * @return
     */
    String parse(String plainText);
}

