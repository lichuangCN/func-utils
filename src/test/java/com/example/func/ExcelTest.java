package com.example.func;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.example.func.util.ExcelUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichuang
 * @date 2023/01/17
 */
@Slf4j
public class ExcelTest {
    public static void main(String[] args) {

    }

    /**
     * 生成Excel文件 多个Sheet
     */
    private void writeExcels(String path) throws Exception {

        ExcelUtil.MultiSheetData<Excel20230117> sheet1 = ExcelUtil.buildMultiSheet();
        sheet1.setData(new ArrayList<>());
        sheet1.setClazz(Excel20230117.class);
        sheet1.setSheetName("sheet1");

        ExcelUtil.MultiSheetData<Excel20230117> sheet2 = ExcelUtil.buildMultiSheet();
        sheet2.setData(new ArrayList<>());
        sheet2.setClazz(Excel20230117.class);
        sheet2.setSheetName("sheet2");

        List<ExcelUtil.MultiSheetData<Excel20230117>> sheets = new ArrayList<>();
        sheets.add(sheet1);
        sheets.add(sheet2);

        ExcelUtil.exportMultiSheet(path, sheets);
    }

    /**
     * 生成Excel文件 单个Sheet
     */
    private void writeExcel(List<Excel20230117> data, String path) throws Exception {
        ExcelUtil.export(path, data, Excel20230117.class);
    }

    /**
     * 读取Excel文件
     */
    private List<Excel20230117> readExcel(String path, String idx) throws Exception {
        List<Excel20230117> list = ExcelUtil.read(path, Excel20230117.class);
        return list;
    }

    @Data
    public static class Excel20230117 {
        @Excel(name = "客户编码")
        private String clientNumber;
        @Excel(name = "客户名称")
        private String clientAlias;
    }
}
