package com.example.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

import kotlin.text.Regex;
import kotlin.text.RegexKt;

public class FileUtil {
    public static void main(String[] args) {
        String str = "'ADFSAF\\\'DFADF";
        System.out.println(str);
        str = str.replaceAll("\\\\'","'").replaceAll("'","\\\\'");

        System.out.println(str);
    }
    public static String readFile(String fileNamePath) {
        File file = new File(fileNamePath);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writerFile(String filePath,String str) {
        File writerFile = new File(filePath);
        try {
            if (!writerFile.exists()) {
                writerFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(writerFile);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据文件路径拷贝文件
     * @param src 源文件
     * @param destPath 目标文件路径
     * @return boolean 成功true、失败false
     */
    public static boolean copyFile(File src, String destPath) {
        if ((src == null) || (destPath== null)) {
            return false;
        }
        File dest = new File(destPath);
        if (dest.exists()) {
            boolean isSuccess = dest.delete();
            if (!isSuccess) {
                return false;
            }
        }
        try {
            boolean isSuccess = dest.createNewFile();
            if (!isSuccess) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        boolean result = false;
        FileChannel srcChannel = null;
        FileChannel dstChannel = null;
        try {
            srcChannel = new FileInputStream(src).getChannel();
            dstChannel = new FileOutputStream(dest).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (srcChannel != null) {
                    srcChannel.close();
                }
                if (dstChannel != null) {
                    dstChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
