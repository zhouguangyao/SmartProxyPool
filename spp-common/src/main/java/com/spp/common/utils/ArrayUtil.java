package com.spp.common.utils;

/**
 * 数组工具类
 *
 * @author zhougy
 * @date 2024/05/14
 */
public class ArrayUtil {
    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     *                * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return ObjectUtil.isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return ObjectUtil.isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 字符串转数组
     *
     * @param str 字符串
     * @param sep 分隔符
     * @return
     */
    public static String[] strToArrAy(String str, String sep) {
        return StringUtils.isEmpty(str) ? null : str.split(sep);
    }
}
