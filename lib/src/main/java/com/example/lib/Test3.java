package com.example.lib;

import com.example.lib.utils.TimeUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Test3 {
    public static String DVR_FILE_MARK = "DVR_";
    public static void main(String[] args) {

        //1677898270927 2023-03-04 10:51:10    1677916490958 2023-03-04 15:54:50
        long time = 1677916127986L + 10 * 1000;

        time = 1677916490958L;
        long duration = 10 * 1000;


        File recordDir = new File("D:\\BaiduNetdiskDownload");
        File[] files = recordDir.listFiles();
        String frontTag = "_front.mp4";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


        long startTime = time - duration;
        List<DataBean<Long, File>> list = new ArrayList<>();
        System.out.println("时间:" + TimeUtils.getTimeStr(time) + " duration:" + duration);
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if (name.contains(DVR_FILE_MARK) && name.contains(frontTag)) {
                if (files[i].isFile() && files[i].exists()) {
                    String timeStr = name.substring(name.lastIndexOf(DVR_FILE_MARK) + 4, name.lastIndexOf(frontTag));
                    // 行程录像视频开始录制的时间戳
                    long recordStartTime = Long.parseLong(timeStr);
                    long recordEndTime = recordStartTime + 3 * 60 * 1000;

                    boolean accept = recordEndTime > startTime && recordStartTime < time;
                    if (accept) {
                        System.out.println("时间1:" + sdf.format(new Date(recordStartTime)) + " === " + sdf.format(new Date(files[i].lastModified())) + " == " + timeStr);
                        list.add(new DataBean<>(recordStartTime, files[i]));
                    } else {
                        System.out.println("时间2:" + sdf.format(new Date(recordStartTime)) + " === " + sdf.format(new Date(files[i].lastModified())) + " == " + timeStr);
                    }
                }
            }
        }

        Collections.sort(list, new Comparator<DataBean<Long, File>>() {
            @Override
            public int compare(DataBean<Long, File> o1, DataBean<Long, File> o2) {
                return o1.field1.compareTo(o2.field1);
            }
        });

        System.out.println(" end " + list.size());
    }
}
