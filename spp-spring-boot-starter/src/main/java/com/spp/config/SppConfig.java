package com.spp.config;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/2/23 15:43
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spp")
public class SppConfig {


    /** 禁用 */
    private String disableCrawler;
}
