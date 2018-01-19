package com.pafer.distributed.local.cache;

/**
 * @author wangzhenya
 */
public interface DayTime {

    int SECOND = 1;
    int MINUTE = 60;
    int HOUR = 60 * MINUTE;
    int DAY = 24 * HOUR;
    int WEEK = 7 * DAY;
    int MONTH = 30 * DAY;
    int YEAR = 12 * MONTH;
}
