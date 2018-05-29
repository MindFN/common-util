package com.util.io.excel;

import com.google.common.collect.Lists;
import com.util.io.AbstractFileOperator;
import com.util.io.annotation.FileField;
import com.util.io.rule.funciton.BaseFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wulang
 * @Date: 2017年10月14日 15:25
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class ExcelWriter extends AbstractFileOperator {
    private static class ExcelWriterHolder {
        private static final ExcelWriter INSTANCE = new ExcelWriter();
    }

    private ExcelWriter() {
    }

    /**
     * ExcelWriter实例
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:06
     * @modify by user: {修改人}  2017/10/14 16:06
     * @modify by reason:
     */
    public static ExcelWriter getInstance() {
        return ExcelWriterHolder.INSTANCE;
    }

    /**
     * 写出数据到Excel xlsx格式
     *
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:53
     * @modify by user: {修改人}  2017/10/14 16:53
     * @modify by reason:
     */
    public <T> Workbook writeDataToXLS(Collection<T> collection, String sheetName, Class<T> type) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        writeDataToXLS(workbook, false, collection, sheetName, type);
        return workbook;
    }

    /**
     * 写出数据到 指定Excel XLS格式
     *
     * @param workbook
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:07
     * @modify by user: {修改人}  2017/10/14 16:07
     * @modify by reason:
     */
    public <T> Workbook writeDataToXLS(Workbook workbook, boolean useNewSheet, Collection<T> collection, String sheetName, Class<T> type) {
        writeFileDate(workbook, useNewSheet, collection, sheetName, type, false);
        return workbook;
    }

    /**
     * 写出数据到Excel  XLSX格式
     *
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:53
     * @modify by user: {修改人}  2017/10/14 16:53
     * @modify by reason:
     */
    public <T> Workbook writeDataToXLSX(Collection<T> collection, String sheetName, Class<T> type) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelConstant.BATCH_WRITE_SIZE);
        writeDataToXLSX(workbook, false, collection, sheetName, type);
        return workbook;
    }

    /**
     * 写出数据到 指定Excel XLSX格式
     *
     * @param workbook
     * @param collection
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:07
     * @modify by user: {修改人}  2017/10/14 16:07
     * @modify by reason:
     */
    public <T> Workbook writeDataToXLSX(Workbook workbook, boolean useNewSheet, Collection<T> collection, String sheetName, Class<T> type) {
        writeFileDate(workbook, useNewSheet, collection, sheetName, type, true);
        return workbook;
    }

    /**
     * 写出数据到Excel细节
     *
     * @param workbook
     * @param useNewSheet
     * @param collection
     * @param type
     * @param isXLSX
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:04
     * @modify by user: {修改人}  2017/10/14 17:04
     * @modify by reason:
     */
    private <T> void writeFileDate(Workbook workbook, boolean useNewSheet, Collection<T> collection, String sheetName, Class<T> type, boolean isXLSX) {
        int rIndex = -1;
        int rNum = 0;
        int startIndex = 0;
        int batchSize = 0;
        Collection<T> subList = null;
        Sheet sheet = null;
        int sIndex = workbook.getNumberOfSheets();
        if (0 < sIndex) {
            sheet = workbook.getSheetAt(sIndex - 1);
        }
        CellStyle titleStyle = getTitleStyle(workbook);
        CellStyle dataStyle = getDateStyle(workbook);
        List<FieldDescriptorBean> fieldDescriptorBeanList = fetchFieldDescriptorList(type);
        if (!useNewSheet) {
            rNum = null != sheet ? sheet.getPhysicalNumberOfRows() : -1;
            rIndex = rNum < (isXLSX ? ExcelConstant.XLSX_MAX_ROW : ExcelConstant.XLS_MAX_ROW) ? rNum : -1;
        }
        do {
            if (-1 == rIndex) {
                sheetName = StringUtils.isEmpty(sheetName) ? "sheet" : sheetName;
                sheet = workbook.createSheet(sheetName + sIndex++);
                rNum = 1;
                writeSheetTitle(sheet, titleStyle, isXLSX, fieldDescriptorBeanList);
            }
            batchSize = isXLSX ? ExcelConstant.XLSX_MAX_ROW - rNum : ExcelConstant.XLS_MAX_ROW - rNum;
            subList = subCollection(collection, startIndex, batchSize);
            startIndex += batchSize;
            rIndex = ((startIndex + rNum) < (isXLSX ? ExcelConstant.XLSX_MAX_ROW : ExcelConstant.XLS_MAX_ROW)) ? startIndex : -1;
            writeDataToSheet(sheet, dataStyle, isXLSX, fieldDescriptorBeanList, subList);
        } while (startIndex < collection.size());
    }

    /**
     * 写出数据到sheet
     *
     * @param sheet
     * @param dataStyle
     * @param isXLSX
     * @param fieldDescriptorBeanList
     * @param collection
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:05
     * @modify by user: {修改人}  2017/10/14 17:05
     * @modify by reason:
     */
    private <T> void writeDataToSheet(Sheet sheet, CellStyle dataStyle, boolean isXLSX, List<FieldDescriptorBean> fieldDescriptorBeanList, Collection<T> collection) {
        Row row;
        T bean;
        int rIndex = sheet.getPhysicalNumberOfRows();
        int maxSheetCol = isXLSX ? ExcelConstant.XLSX_MAX_COL : ExcelConstant.XLS_MAX_COL;
        int maxCol = fieldDescriptorBeanList.size() < maxSheetCol ? fieldDescriptorBeanList.size() : maxSheetCol;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            row = sheet.createRow(rIndex++);
            bean = iterator.next();
            writeRow(row, maxCol, dataStyle, isXLSX, fieldDescriptorBeanList, bean);
        }
    }

    /**
     * 写出数据行
     *
     * @param row
     * @param maxCol
     * @param dataStyle
     * @param isXLSX
     * @param fieldDescriptorBeanList
     * @param bean
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:05
     * @modify by user: {修改人}  2017/10/14 17:05
     * @modify by reason:
     */
    private <T> void writeRow(Row row, int maxCol, CellStyle dataStyle, boolean isXLSX, List<FieldDescriptorBean> fieldDescriptorBeanList, T bean) {
        int cIndex = 0;
        Cell cell = null;
        FieldDescriptorBean fieldDescriptorBean = null;
        while (cIndex < maxCol) {
            fieldDescriptorBean = fieldDescriptorBeanList.get(cIndex);
            cell = row.createCell(cIndex++);
            writeCell(cell, dataStyle, fieldDescriptorBean, bean);
        }

    }

    /**
     * 写出单元格数据
     *
     * @param cell
     * @param dataStyle
     * @param fieldDescriptorBean
     * @param bean
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:05
     * @modify by user: {修改人}  2017/10/14 17:05
     * @modify by reason:
     */
    private <T> void writeCell(Cell cell, CellStyle dataStyle, FieldDescriptorBean fieldDescriptorBean, T bean) {
        Method method = fieldDescriptorBean.getGetMethod();
        Class<?> fieldType = fieldDescriptorBean.getType();
        FileField xlsField = fieldDescriptorBean.getFileField();
        Object value = null;
        try {
            value = method.invoke(bean);
            if (null != xlsField) {
                BaseFunction function = xlsField.wRule().getFunction();
                value = function.apply(value);
            }
            if (null == value) {
                return;
            }
            if ((null != xlsField && String.class == xlsField.newType()) || fieldType.isAssignableFrom(String.class)) {
                if (value.toString().length() > ExcelConstant.DATA_MAX_LENGTH) {
                    cell.setCellValue("数据长度超出单元格大小范围，请联系管理员检查数据项");
                } else {
                    cell.setCellValue(value.toString());
                }
                return;
            }
            if (fieldType.isAssignableFrom(Double.TYPE) || fieldType.isAssignableFrom(Double.class)) {
                cell.setCellValue((Double) value);
            }
            if (fieldType.isAssignableFrom(Integer.TYPE) || fieldType.isAssignableFrom(Integer.class)) {
                cell.setCellValue((Integer) value);
            }
            if (fieldType.isAssignableFrom(Float.TYPE) || fieldType.isAssignableFrom(Float.class)) {
                cell.setCellValue((Float) value);
            }
            if (fieldType.isAssignableFrom(Character.TYPE) || fieldType.isAssignableFrom(Character.class)) {
                cell.setCellValue((Character) value);
            }
            if (fieldType.isAssignableFrom(Short.TYPE) || fieldType.isAssignableFrom(Short.class)) {
                cell.setCellValue((Short) value);
            }
            if (fieldType.isAssignableFrom(Byte.TYPE) || fieldType.isAssignableFrom(Byte.class)) {
                cell.setCellValue((Byte) value);
            }
            if (fieldType.isAssignableFrom(Boolean.TYPE) || fieldType.isAssignableFrom(Boolean.class)) {
                cell.setCellValue((Boolean) value);
            }
            if (fieldType.isAssignableFrom(Long.TYPE) || fieldType.isAssignableFrom(Long.class)) {
                cell.setCellValue((Long) value);
            }
            if (Date.class.isAssignableFrom(fieldType)) {
                cell.setCellValue((Date) value);
            }
            cell.setCellStyle(dataStyle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写sheet的头
     *
     * @param sheet
     * @param titleStyle
     * @param isXLSX
     * @param fieldDescriptorBeanList
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:09
     * @modify by user: {修改人}  2017/10/14 17:09
     * @modify by reason:
     */
    private void writeSheetTitle(Sheet sheet, CellStyle titleStyle, boolean isXLSX, List<FieldDescriptorBean> fieldDescriptorBeanList) {
        FileField fileField = null;
        String colName = null;
        FieldDescriptorBean fieldDescriptorBean = null;
        int cIndex = 0;
        int maxSheetCol = isXLSX ? ExcelConstant.XLSX_MAX_COL : ExcelConstant.XLS_MAX_COL;
        int maxCol = fieldDescriptorBeanList.size() < maxSheetCol ? fieldDescriptorBeanList.size() : maxSheetCol;
        Cell cell = null;
        Row row = sheet.createRow(0);
        while (cIndex < maxCol) {
            fieldDescriptorBean = fieldDescriptorBeanList.get(cIndex);
            //获取列名，未指定则使用field作为列名
            fileField = fieldDescriptorBean.getFileField();
            colName = null == fileField ? "" : fileField.name();
            colName = StringUtils.isBlank(colName) ? fieldDescriptorBean.getField().getName() : colName;
            cell = row.createCell(cIndex++);
            cell.setCellValue(colName);
            cell.setCellStyle(titleStyle);
        }
    }

    /**
     * 截取指定长度的子容器
     *
     * @param collection
     * @param startIndex
     * @param batchSize
     * @return
     * @author: wulang
     * @date: 2017/10/14 17:09
     * @modify by user: {修改人}  2017/10/14 17:09
     * @modify by reason:
     */
    private <T> Collection<T> subCollection(Collection<T> collection, int startIndex, int batchSize) {
        int toIndex = startIndex + batchSize;
        toIndex = toIndex > collection.size() ? collection.size() : toIndex;
        List<T> list = Lists.newArrayList(collection);
        return list.subList(startIndex, toIndex);
    }


    /**
     * 默认标题风格
     *
     * @param workbook
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:58
     * @modify by user: {修改人}  2017/10/14 16:58
     * @modify by reason:
     */
    private CellStyle getTitleStyle(Workbook workbook) {
        Font titleFont = workbook.createFont();
        CellStyle titleStyle = workbook.createCellStyle();
        titleFont.setColor(XSSFFont.COLOR_NORMAL);
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setFontName("宋体");
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    /**
     * 默认数据格式风格
     *
     * @param workbook
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:58
     * @modify by user: {修改人}  2017/10/14 16:58
     * @modify by reason:
     */
    private CellStyle getDateStyle(Workbook workbook) {
        Font dataFont = workbook.createFont();
        CellStyle cellStyle = workbook.createCellStyle();
        CellStyle dataStyle = workbook.createCellStyle();
        dataFont.setColor(XSSFFont.COLOR_NORMAL);
        dataFont.setFontHeightInPoints((short) 12);
        dataFont.setFontName("宋体");
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setFont(dataFont);
        return cellStyle;
    }
}
