package com.spp.common.utils;

import com.spp.common.enums.DateField;

import java.util.Calendar;
import java.util.Date;

/**
 * @author warm
 * @description: 数组工具类
 * @date: 2023/5/18 9:45
 */
public class DateUtil {
    public static Date offset(DateField datePart, int offset) {
        // 创建一个Calendar实例，并用当前时间初始化
        Calendar calendar = Calendar.getInstance();
        // 设置calendar的时间为传入的Date
        calendar.setTime(new Date());

        // 根据datePart应用偏移量
        switch (datePart) {
            case YEAR:
                calendar.add(Calendar.YEAR, offset);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, offset);
                break;
//            case DAY:
//                calendar.add(Calendar.DATE, offset);
//                break;
            case HOUR:
                calendar.add(Calendar.HOUR, offset);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, offset);
                break;
            case SECOND:
                calendar.add(Calendar.SECOND, offset);
                break;
            default:
                throw new IllegalArgumentException("Unsupported date part: " + datePart);
        }

        // 返回更新后的日期
        return calendar.getTime();
    }

    // 测试方法
    public static void main(String[] args) {
        Date newDate = DateUtil.offset(DateField.HOUR, 5);
        System.out.println(newDate);
    }
}
