package com.util.io.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * @author: wulang
 * @date: 2017年10月14日 15:26
 * @version: v1.0
 * @description:
 * @modified By:
 * @modifued reason
 */
public class ExcelUtil {
    /**
     * excel写出对象
     */
    private static ExcelWriter excelWriter;
    /**
     * excel读取对象
     */
    private static ExcelReader excelReader;

    /**
     * 写出数据到Excel 默认xlsx格式
     *
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 15:59
     * @modify by user: {修改人}  2017/10/14 15:59
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTable(Collection<T> collection, Class<T> type, String sheetName) {
        checkWriter();
        return excelWriter.writeDataToXLSX(collection, sheetName, type);
    }

    /**
     * 写出数据到Excel 指定格式
     *
     * @param collection
     * @param type
     * @param isXLSX
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:00
     * @modify by user: {修改人}  2017/10/14 16:00
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTable(Collection<T> collection, Class<T> type, String sheetName, boolean isXLSX) {
        checkWriter();
        return isXLSX ? excelWriter.writeDataToXLSX(collection, sheetName, type) : excelWriter.writeDataToXLS(collection, sheetName, type);
    }

    /**
     * 写出数据到Excel xlsx格式,默认新写入的数据不另外创建sheet
     *
     * @param workbook
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:00
     * @modify by user: {修改人}  2017/10/14 16:00
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTableXLSX(Workbook workbook, Collection<T> collection, String sheetName, Class<T> type) {
        checkWriter();
        return writeDataToTableXLSX(workbook, false, collection, sheetName, type);
    }

    /**
     * 写出数据到Excel xlsx格式,控制是否需要创建新的sheet
     *
     * @param workbook
     * @param newSheet
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:02
     * @modify by user: {修改人}  2017/10/14 17:02
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTableXLSX(Workbook workbook, boolean newSheet, Collection<T> collection, String sheetName, Class<T> type) {
        checkWriter();
        return excelWriter.writeDataToXLSX(workbook, newSheet, collection, sheetName, type);
    }


    /**
     * 写出数据到Excel xls格式 默认数据写入到新的sheet中去
     *
     * @param workbook
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:01
     * @modify by user: {修改人}  2017/10/14 16:01
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTableXLS(Workbook workbook, Collection<T> collection, String sheetName, Class<T> type) {
        checkWriter();
        return writeDataToTableXLS(workbook, false, collection, sheetName, type);
    }

    /**
     * 写出数据到Excel xlsx格式,控制是否需要创建新的sheet
     *
     * @param workbook
     * @param newSheet
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:08
     * @modify by user: {修改人}  2017/10/14 16:08
     * @modify by reason:
     */
    public static <T> Workbook writeDataToTableXLS(Workbook workbook, boolean newSheet, Collection<T> collection, String sheetName, Class<T> type) {
        checkWriter();
        return excelWriter.writeDataToXLS(workbook, newSheet, collection, sheetName, type);
    }

    /**
     * 从xls格式读取文件
     *
     * @param fileInputStream
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:01
     * @modify by user: {修改人}  2017/10/14 16:01
     * @modify by reason:
     */
    public static <T> Collection readDataFromXLS(FileInputStream fileInputStream, Class<T> type) {
        Collection<T> collection = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            collection = excelReader.readDataFromWorkbook(workbook, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * 从XLSX读取数据
     *
     * @param fileInputStream
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:02
     * @modify by user: {修改人}  2017/10/14 16:02
     * @modify by reason:
     */
    public static <T> Collection<T> readDataFromXLSX(FileInputStream fileInputStream, Class<T> type) {
        Collection<T> collection = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            collection = excelReader.readDataFromWorkbook(workbook, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:02
     * @modify by user: {修改人}  2017/10/14 16:02
     * @modify by reason:
     */
    private static void checkWriter() {
        if (null == excelWriter) {
            excelWriter = ExcelWriter.getInstance();
        }
    }

    /**
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:02
     * @modify by user: {修改人}  2017/10/14 16:02
     * @modify by reason:
     */
    private static void checkReader() {
        if (null == excelReader) {
            excelReader = ExcelReader.getInstance();
        }
    }
}
