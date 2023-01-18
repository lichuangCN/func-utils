package com.example.func.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lichuang
 * @date 2021/12/12
 */
@Slf4j
public class FileUtil {

    /**
     * 字符串写入到文件
     */
    public static void write(String data, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data.getBytes());
        fos.close();
    }

    /**
     * 字符串写入到文件
     */
    public static void write(List<String> data, String filePath) throws Exception {
        StringBuilder builder = new StringBuilder();
        data.forEach(e -> builder.append(e).append("\r\n"));
        String file = builder.toString();

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(file.getBytes());
        fos.close();
    }

    /**
     * 字符串写入到文件拆分文件
     */
    public static void write(List<String> data, String filePath, int size) throws Exception {
        int idx = filePath.lastIndexOf(".");
        String fileName = filePath.substring(0, idx);
        String fileType = filePath.substring(idx);
        List<List<String>> split = ListUtils.partition(data, size);

        for (int i = 0; i < split.size(); i++) {
            List<String> list = split.get(i);
            StringBuilder builder = new StringBuilder();
            list.forEach(e -> builder.append(e).append("\r\n"));
            String file = builder.toString();
            String realFileName = fileName + "_" + (i + 1) + fileType;
            FileOutputStream fos = new FileOutputStream(realFileName);
            fos.write(file.getBytes());
            fos.close();
        }
    }

    /**
     * 读取字符串(逐行读入，返回每行的数据)
     */
    public static List<String> readLine(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bis = new BufferedReader(isr);

        String line = null;
        line = bis.readLine();
        while (line != null) {
            result.add(line);
            // 读取下一行
            line = bis.readLine();
        }
        bis.close();
        isr.close();
        fis.close();
        return result;
    }
}
