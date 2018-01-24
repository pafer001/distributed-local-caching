package com.pafer.distributed.local.cache.tools;


import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.Date;

public class DateUtils {


    public static String getMMMDDYYYYHHMMDate() {
        Date date = new Date();
        return Joiner.on("-").join(Lists.newArrayList(date.getYear(), date.getMonth(),
                date.getDate(), date.getHours(), date.getMinutes(), date.getMinutes()));
    }

}
