package com.example.func.util;


import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate,LocalDateTime,LocalTime 工具类
 */
@Slf4j
public class LocalDateUtil {
    private LocalDateUtil() {
    }

    /**
     * 定义常量
     **/
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";

    private static final DateTimeFormatter LOCAL_DATE_FMT = DateTimeFormatter.ofPattern(DATE_SMALL_STR);
    private static final DateTimeFormatter LOCAL_DATE_TIME_FMT = DateTimeFormatter.ofPattern(DATE_FULL_STR);

    /**
     * 将LocalDate 转为yyyy-MM-dd 格式的字符串
     */
    public static String toStr(LocalDate localDate) {
        if (localDate == null) {
            return "";
        }
        return LOCAL_DATE_FMT.format(localDate);
    }


    /**
     * 将LocalDate转为自定义的时间格式的字符串
     */
    public static String toStr(LocalDate localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * 将LocalDateTime 转为 yyyy-MM-dd HH:mm:ss 格式的字符串
     */
    public static String toStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        return LOCAL_DATE_TIME_FMT.format(localDateTime);
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     */
    public static String toStr(LocalDateTime localDateTime, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    /**
     * 将LocalDateTime转为long类型的毫秒数
     */
    public static long getTimestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }
}
