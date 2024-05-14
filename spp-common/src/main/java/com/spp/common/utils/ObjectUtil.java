package com.spp.common.utils;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

/**
 * 对象工具类
 * @author zhougy
 * @date 2024/05/14
 */
public class ObjectUtil {

    /**
     * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    /**
     * 判断字符串是否为true
     *
     * @param str
     * @return
     */
    public static boolean isStrTrue(String str) {
        return StringUtils.isNotEmpty(str) && "true".equals(str);
    }

    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }

        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        } else if (obj instanceof Map) {
            Map objMap = (Map) obj;
            return null == objMap || objMap.isEmpty();
        } else if (ArrayUtil.isArray(obj)) {
            return 0 == Array.getLength(obj);
        }

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return false == isEmpty(obj);
    }
}
