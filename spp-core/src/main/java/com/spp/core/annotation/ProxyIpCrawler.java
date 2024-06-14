
package com.spp.core.annotation;

import com.spp.common.enums.DateField;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.strategy.AbstractParserStrategy;
import com.spp.core.strategy.DefaultParser;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 代理ip爬虫注解
 *
 * @author zhougy
 * @date 2022/6/20 14:25
 **/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProxyIpCrawler {

    /** 默认 **/
    String value() default "";

    /** 是否开启 **/
    boolean enable() default true;

    /** 唯一值 **/
    String key() default "";

    /** 名称 **/
    String name() default "";

    /** 地址 **/
    String baseUrl() default "";

    /** 总页数 **/
    int pages() default 1;

    /** 页码别称字典json **/
    String pageNoDict() default "";

    /** 爬虫类型 **/
    IpCrawlerTypeEnum type() default IpCrawlerTypeEnum.HTML;

    /** 行解析器（JSON） **/
    String rowsParser() default "";

    /** ip解析器（JSON） **/
    String ipParser() default "ip";

    /** 端口解析器（JSON） **/
    String portParser() default "port";

    /** 城市解析器（JSON） **/
    String cityParser() default "city";

    /** 行选择器（HTML） **/
    String rowsSelector() default "";

    /** cell选择器（HTML） **/
    String cellSelector() default "";

    /** 表头下标 **/
    int headRowIndex() default 0;

    /** ip下标 **/
    int ipIndex() default 0;

    /** ip值策略 **/
    Class<? extends AbstractParserStrategy> ipValueParser() default DefaultParser.class;

    /** 端口下标 **/
    int portIndex() default 1;

    /** 端口值策略 **/
    Class<? extends AbstractParserStrategy> portValueParser() default DefaultParser.class;

    /** 城市下标 **/
    int cityIndex() default 2;

    /** 城市解析策略 **/
    Class<? extends AbstractParserStrategy> cityNameParser() default DefaultParser.class;

    /** 过期时间单位 **/
    DateField expireUnit() default DateField.MINUTE;

    /** 过期时间偏移值 **/
    int expireOffset() default 10;

    /** 是否上锁 **/
    boolean lock() default false;

}
