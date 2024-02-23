package com.example.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static String getTimeStr(long time) {
        return sdf.format(new Date(time));
    }
}
