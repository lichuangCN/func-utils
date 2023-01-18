package com.example.func;

import com.example.func.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lichuang
 * @date 2023/01/17
 */
@Slf4j
public class FileTest {
    public static void main(String[] args) {

    }

    /**
     * 逐行写入文件 多个文件
     */
    private void writes(String path, List<String> data) throws Exception {
        FileUtil.write(data, path, 100);
    }

    /**
     * 逐行写入文件 单个文件
     */
    private void write(String path, List<String> data) throws Exception {
        FileUtil.write(data, path);
    }

    /**
     * 逐行读取文本
     */
    private List<String> readFile(String path) throws Exception {
        List<String> list = FileUtil.readLine(path);
        return list;
    }
}
