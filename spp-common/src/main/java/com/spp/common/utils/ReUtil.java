package com.spp.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/5/14 17:34
 */
public class ReUtil {
    public static String get(String regex, CharSequence content, int groupIndex) {
        if (null == content || null == regex) {
            return null;
        }

        final Pattern pattern = Pattern.compile(regex);
        String group = null;
        final Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            group = matcher.group(groupIndex);
        }

        return group;
    }
}
