/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 18:46
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.csv;

import com.Ostermiller.util.CSVPrinter;
import com.util.io.AbstractFileOperator;
import com.util.io.annotation.FileBean;
import com.util.io.annotation.FileField;
import com.util.io.rule.funciton.BaseFunction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 18:46
 * @description
 * @modified By:
 * @modifued reason:
 */
class CsvWriter extends AbstractFileOperator {
    /**
     * 默认的值分隔符号
     */
    private static final String DEFAULT_VALUE_SEPARATE = "/";

    /**
     * 写出数据到csv
     *
     * @param outputStream
     * @param collection
     * @param hasTitle
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 19:32
     * @modify by user: {修改人}  2018/1/16 19:32
     * @modify by reason:
     */
    <T> void writeDataToCsv(OutputStream outputStream, Charset charset, Collection<T> collection, boolean hasTitle, Class<T> type) throws IOException {
        if (null == outputStream || null == type) {
            return;
        }
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream, charset));
        List<FieldDescriptorBean> fieldDescriptorBeanList = fetchFieldDescriptorList(type);
        if (hasTitle) {
            writeTitle(csvPrinter, type, fieldDescriptorBeanList);
        }
        if (CollectionUtils.isNotEmpty(collection)) {
            for (T target : collection) {
                writeRow(csvPrinter, fieldDescriptorBeanList, target);
            }
        }
    }

    static CsvWriter getInstance() {
        return CsvWriterHolder.INSTANCE;
    }

    /**
     * 写出行数据
     *
     * @param csvPrinter
     * @param fieldDescriptorBeanList
     * @param bean
     * @return
     * @author wulang
     * @date 2018/1/16 21:54
     * @modify by user: {修改人}  2018/1/16 21:54
     * @modify by reason:
     */
    private <T> void writeRow(CSVPrinter csvPrinter, List<FieldDescriptorBean> fieldDescriptorBeanList, T bean) throws IOException {
        String[] rowDataArr = new String[fieldDescriptorBeanList.size()];
        for (int i = 0; i < fieldDescriptorBeanList.size(); i++) {
            FieldDescriptorBean fieldDescriptorBean = fieldDescriptorBeanList.get(i);
            String cellValue = fetchCellValue(fieldDescriptorBean, bean);
            rowDataArr[i] = cellValue;
        }
        csvPrinter.println(rowDataArr);
    }

    /**
     * 获取某一个具体的属性值
     *
     * @param fieldDescriptorBean
     * @param bean
     * @author wulang
     * @date 2018/1/16 19:33
     * @modify by user: {修改人}  2018/1/16 19:33
     * @modify by reason:
     */
    private <T> String fetchCellValue(FieldDescriptorBean fieldDescriptorBean, T bean) {
        Method getMethod = fieldDescriptorBean.getGetMethod();
        FileField fileField = fieldDescriptorBean.getFileField();
        Object sourceValue = null;
        Object applyValue = null;
        Object cellValue = null;
        try {
            sourceValue = getMethod.invoke(bean);
            if (null != sourceValue && null != fileField) {
                BaseFunction function = fileField.wRule().getFunction();
                applyValue = function.apply(sourceValue, fieldDescriptorBean.getType());
            }
            if (null != applyValue && !applyValue.toString().isEmpty() && fileField.transform()) {
                cellValue = transformValue(applyValue);
            } else {
                cellValue = applyValue;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            cellValue = applyValue;
            if (null == cellValue) {
                cellValue = sourceValue;
            }
            if (null == cellValue && null != fileField && fileField.defaultValue().length() > 0) {
                cellValue = fileField.defaultValue();
            }
        }
        return Optional.ofNullable(cellValue).map(Object::toString).orElse("");
    }

    /**
     * 进行国际化转换
     *
     * @param sourceValue 源值
     * @return java.lang.String
     * @author wulang
     * @date 2018/1/23 12:42
     * @modify by user: {修改人}  2018/1/23 12:42
     * @modify by reason:
     */
    private String transformValue(Object sourceValue) {
        String i18nValue = "";
        String tempValue = "";
        StringBuilder valueBuilder = new StringBuilder();
        if (sourceValue instanceof Collection) {
            for (Object tempObj : (Collection) sourceValue) {
//                i18nValue = I18nUtil.getMessage(tempObj.toString());
//                i18nValue = tempObj.toString();
                tempValue = StringUtils.isNotBlank(i18nValue) ? i18nValue : tempObj.toString();
                valueBuilder.append(tempValue).append(DEFAULT_VALUE_SEPARATE);
            }
            tempValue = valueBuilder.toString();
            tempValue = StringUtils.isNotBlank(tempValue) ? tempValue.substring(0, tempValue.lastIndexOf(DEFAULT_VALUE_SEPARATE)) : tempValue;
        } else {
//            i18nValue = I18nUtil.getMessage(sourceValue.toString());
//            i18nValue = sourceValue.toString();
            tempValue = StringUtils.isNotBlank(i18nValue) ? i18nValue : sourceValue.toString();
        }
        return tempValue;
    }

    /**
     * 写出头信息
     *
     * @param csvPrinter
     * @param fieldDescriptorBeanList
     * @return
     * @author wulang
     * @date 2018/1/16 19:29
     * @modify by user: {修改人}  2018/1/16 19:29
     * @modify by reason:
     */
    private void writeTitle(CSVPrinter csvPrinter, Class<?> type, List<FieldDescriptorBean> fieldDescriptorBeanList) throws IOException {
        String colName = null;
        String colValue = null;
        FileBean fileBean = type.getAnnotation(FileBean.class);
        FileField fileField = null;
        String[] rowDataArr = new String[fieldDescriptorBeanList.size()];
        for (int i = 0; i < fieldDescriptorBeanList.size(); i++) {
            FieldDescriptorBean fieldDescriptorBean = fieldDescriptorBeanList.get(i);
            fileField = fieldDescriptorBean.getFileField();
            //取列名-->FileField标签无或者name为null时，取字段名为列名
            colName = null != fileField ? fileField.name() : null;
            colName = StringUtils.isBlank(colName) ? fieldDescriptorBean.getField().getName() : colName;
            if (null != fileBean && fileBean.transform() && null != fileField) {
                try {
//                    colValue = I18nUtil.getMessage(colName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rowDataArr[i] = StringUtils.isNotBlank(colValue) ? colValue : colName;
        }
        csvPrinter.println(rowDataArr);
    }

    private static class CsvWriterHolder {
        private static final CsvWriter INSTANCE = new CsvWriter();
    }

}
