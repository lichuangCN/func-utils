package com.example.func.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author lichuang
 * @date 2021/10/29
 */
@Slf4j
public class ExcelUtil {

    /**
     * sheet实体类
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultiSheetData<T> {
        private String sheetName;
        private List<T> data;
        private Class<T> clazz;
    }

    public static MultiSheetData buildMultiSheet() {
        return new ExcelUtil.MultiSheetData<T>();
    }

    /**
     * 读取文件，转为List
     */
    public static <T> List<T> read(String filePath, Class<T> clazz) throws Exception {
        List<T> list = read(filePath, clazz, 0);
        return list;
    }

    /**
     * 读取文件，转为List
     */
    public static <T> List<T> read(String filePath, Class<T> clazz, int sheetIdx) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ImportParams params = new ImportParams();
        // 指定第几个sheet,第一个sheet为0
        params.setStartSheetIndex(sheetIdx);
        List list = ExcelImportUtil.importExcel(fis, clazz, params);
        log.info("共读取{}条数据", list.size());
        return list;
    }

    /**
     * 单个sheet导出，默认sheet名称
     */
    public static <T> void export(String filePath, List<T> dataList, Class<T> clazz) throws Exception {
        if (CollUtil.isEmpty(dataList)) {
            log.info("无数据需要导出");
            return;
        }
        MultiSheetData<T> sheetData = new ExcelUtil.MultiSheetData<>();
        sheetData.setSheetName("sheet1");
        sheetData.setData(dataList);
        sheetData.setClazz(clazz);
        exportMultiSheet(filePath, Arrays.asList(sheetData));
    }

    /**
     * 单个sheet导出
     */
    public static <T> void exportSingleSheet(String filePath, MultiSheetData<T> sheetData) throws Exception {
        exportMultiSheet(filePath, Arrays.asList(sheetData));
    }

    /**
     * 多个sheet导出
     */
    public static <T> void exportMultiSheet(String filePath, List<MultiSheetData<T>> sheetDataList) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        for (MultiSheetData<T> sheetData : sheetDataList) {
            if (CollUtil.isEmpty(sheetData.getData())) {
                continue;
            }
            ExcelExportService service = new ExcelExportService();
            ExportParams params = new ExportParams();
            params.setSheetName(sheetData.getSheetName());
            // 数据集合不能是通过Arrays.asList()方法构建，否则会抛出java.lang.UnsupportedOperationException
            service.createSheet(workbook, params, sheetData.getClazz(), sheetData.getData());
        }
        String[] split = filePath.split("\\\\");
        String fileName = split[split.length - 1];
        String fileDir = filePath.substring(0, filePath.length() - fileName.length());

        execExport(workbook, fileDir, fileName);
    }

    /**
     * 执行导出操作
     */
    private static void execExport(Workbook workbook, String fileDir, String fileName) throws Exception {
        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(fileDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            fos = new FileOutputStream(fileDir + fileName);
            workbook.write(fos);
        } catch (Exception e) {
            log.error("导出失败：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }
}
