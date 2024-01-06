package com.example.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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



}
