package com.spp.core.strategy;

import com.spp.common.utils.ObjectUtil;
import com.spp.common.utils.ReUtil;

import java.util.Base64;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/6/13 18:30
 */
public class Base64Parser extends AbstractParserStrategy {
    @Override
    public String parse(String plainText) {
        // <script type="text/javascript">document.write(Base64.decode("MTQuMTE1LjEwNS44Mg=="))</script>

        // 正则表达式匹配Base64.decode中的字符串
        String regex = "Base64.decode\\(\"(.*?)\"\\)";
        String matchStr = ReUtil.get(regex, plainText, 1);

        if (ObjectUtil.isEmpty(matchStr)) {
            return matchStr;
        }

        // 解码
        byte[] decodedBytes = Base64.getDecoder().decode(matchStr);

        // 将解码后的字节转换为字符串
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
