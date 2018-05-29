package com.util.io.excel;

import com.google.common.collect.Lists;
import com.util.io.AbstractFileOperator;
import com.util.io.annotation.FileField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author: wulang
 * @Date: 2017年10月14日 15:26
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class ExcelReader extends AbstractFileOperator {
    private static class ExcelReaderHolder {
        private static final ExcelReader INSTANCE = new ExcelReader();
    }

    private ExcelReader() {
    }

    /**
     * ExcelReader实例
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:07
     * @modify by user: {修改人}  2017/10/14 16:07
     * @modify by reason:
     */
    public static ExcelReader getInstance() {
        return ExcelReaderHolder.INSTANCE;
    }


    /**
     * 读取整个文件的数据
     *
     * @param workbook
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 11:38
     * @modify by user: {修改人}  2017/10/14 11:38
     * @modify by reason:
     */
    public <T> List<T> readDataFromWorkbook(Workbook workbook, final Class<T> type) {
        Sheet sheet = null;
        List<T> list = Lists.newArrayList();
        int size = workbook.getNumberOfSheets();
        for (int index = 0; index < size; index++) {
            sheet = workbook.getSheetAt(index);
            list.addAll(readDataFromSheet(sheet, type));
        }
        return list;
    }

    /**
     * 从某张sheet中读取数据封装到bean
     *
     * @param sheet
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 11:37
     * @modify by user: {修改人}  2017/10/14 11:37
     * @modify by reason:
     */
    private <T> Collection<? extends T> readDataFromSheet(Sheet sheet, Class<T> type) {
        T bean;
        Row row = null;
        List<FieldDescriptorBean> fieldDescriptorBeanList = fetchFieldDescriptorList(type);
        int size = sheet.getPhysicalNumberOfRows();
        List<T> list = new ArrayList<>(size);
        for (int rIndex = 1; rIndex < size; rIndex++) {
            row = sheet.getRow(rIndex);
            bean = wrapBean(row, fieldDescriptorBeanList, type);
            list.add(bean);
        }
        return list;
    }

    /**
     * 封装行数据到bean对象中
     *
     * @param row
     * @param fieldDescriptorBeanList
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 11:37
     * @modify by user: {修改人}  2017/10/14 11:37
     * @modify by reason:
     */
    private <T> T wrapBean(Row row, List<FieldDescriptorBean> fieldDescriptorBeanList, Class<T> type) {
        T target = null;
        Method setMethod = null;
        Object value = null;
        FileField fieldAnnotation = null;
        try {
            target = type.newInstance();
            for (FieldDescriptorBean fieldDescriptor : fieldDescriptorBeanList) {
                if (null == fieldDescriptor) {
                    continue;
                }
                value = fetchValue(row.getCell(fieldAnnotation.index()), fieldDescriptor);
                setMethod = fieldDescriptor.getSetMethod();
                setMethod.invoke(target, value);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 从表格中获取值
     *
     * @param cell
     * @param fieldDescriptor
     * @return
     * @author: wulang
     * @date: 2017/10/14 11:36
     * @modify by user: {修改人}  2017/10/14 11:36
     * @modify by reason:
     */
    private Object fetchValue(Cell cell, FieldDescriptorBean fieldDescriptor) {
        Class<?> fieldType = fieldDescriptor.getType();
        Object value = null;
        if (fieldType.isAssignableFrom(Number.class)) {
            value = cell.getNumericCellValue();
        } else if (fieldType.isAssignableFrom(Date.class)) {
            value = cell.getDateCellValue();
        } else if (fieldType.isAssignableFrom(Boolean.class)) {
            value = cell.getBooleanCellValue();
        } else {
            value = cell.getStringCellValue();
        }
        return value;
    }
}
