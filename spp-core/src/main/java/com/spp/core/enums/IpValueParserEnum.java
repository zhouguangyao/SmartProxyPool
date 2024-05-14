package com.spp.core.enums;

import com.spp.common.utils.ObjectUtil;
import com.spp.common.utils.ReUtil;

import java.util.Base64;

/**
 * 解析城市名称的策略接口
 * @author zhougy
 * @date 2022/6/20 14:25
 */
public enum IpValueParserEnum implements ParserStrategy {

    BASE64 {
        @Override
        public String parse(String str) {
            // <script type="text/javascript">document.write(Base64.decode("MTQuMTE1LjEwNS44Mg=="))</script>

            // 正则表达式匹配Base64.decode中的字符串
            String regex = "Base64.decode\\(\"(.*?)\"\\)";
            String matchStr = ReUtil.get(regex, str, 1);

            if (ObjectUtil.isEmpty(matchStr)) {
                return matchStr;
            }

            // 解码
            byte[] decodedBytes = Base64.getDecoder().decode(matchStr);

            // 将解码后的字节转换为字符串
            String decodedString = new String(decodedBytes);
            return decodedString;
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
        // 尝试根据名获取枚举，忽略大小写
        for (IpValueParserEnum strategy : values()) {
            if (strategy.name().equalsIgnoreCase(name)) {
                return strategy;
            }
        }
        // 如果没有匹配的枚举，使用默认策略
        return DEFAULT;
    }
}

