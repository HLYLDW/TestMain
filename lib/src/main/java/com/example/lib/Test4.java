package com.example.lib;

public class Test4 {
    private static final String OSS_OBJECT_FORMAT = "vehicle/%1s/%2s";
    public static void main(String[] args) {
        String objectKey = String.format(OSS_OBJECT_FORMAT, "mVinInfo", "mBean.mVideoPath");
        System.out.println(objectKey);
    }
}
