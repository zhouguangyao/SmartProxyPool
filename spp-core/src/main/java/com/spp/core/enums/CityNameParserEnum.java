package com.spp.core.enums;


import com.spp.common.utils.CityUtils;

/**
 * 解析城市名称的策略接口
 * @author zhougy
 * @date 2022/6/20 14:25
 */
public enum CityNameParserEnum implements ParserStrategy {

    CN_STYLE1 {
        @Override
        public String parse(String str) {
            // 阿里云电信 深圳市
            String city = str.split(" ")[0];
            return city;
        }
    },

    CN_STYLE2 {
        @Override
        public String parse(String str) {
            // 1-中国 北京、2-中国 广东 深圳
            String[] split = str.split(" ");
            String city = split[split.length - 1];
            return city;
        }
    },
    CN_STYLE3 {
        @Override
        public String parse(String str) {
            String city;
            // 阿里云电信 广东省深圳市南山区
            String[] parts = str.split(" ");
            if (parts.length > 1) {
                city = parts[0].substring(parts[0].indexOf("省") + 1);
            } else {
                city = parts[0];
            }
            // 去掉xx省
            city = city.substring(city.indexOf("省") + 1);
            // 去掉xx区
            city = city.substring(0, city.indexOf("市") + 1);
            return city;
        }
    },
    CN_PINYIN {
        @Override
        public String parse(String str) {
            // Shenzhen
            String city = CityUtils.parseCityPinyin(str);
            return city;
        }
    },
    // 默认策略
    DEFAULT {
        @Override
        public String parse(String city) {
            // 默认实现，如果没有匹配的枚举，返回原字符串
            return city;
        }
    };

    public static ParserStrategy getStrategy(String name) {
        // 尝试根据城市名获取枚举，忽略大小写
        for (CityNameParserEnum strategy : values()) {
            if (strategy.name().equalsIgnoreCase(name)) {
                return strategy;
            }
        }
        // 如果没有匹配的枚举，使用默认策略
        return DEFAULT;
    }
}

