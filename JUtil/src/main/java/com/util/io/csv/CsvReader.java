/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 18:45
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.csv;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;
import com.google.common.collect.Lists;
import com.util.io.AbstractFileOperator;
import com.util.io.annotation.FileField;
import com.util.io.rule.funciton.BaseFunction;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 18:45
 * @description
 * @modified By:
 * @modifued reason:
 */
class CsvReader extends AbstractFileOperator {


    /**
     * 输入流中读取数据
     *
     * @param fileInputStream
     * @param charSet
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 20:33
     * @modify by user: {修改人}  2018/1/16 20:33
     * @modify by reason:
     */
    <T> List<T> readDataFromCsv(FileInputStream fileInputStream, Charset charSet, Class<T> type) throws IOException {
        List<T> beanList = Lists.newArrayList();
        if (null != fileInputStream && null != type) {
            LabeledCSVParser csvParser = new LabeledCSVParser(new CSVParser(new BufferedReader(new InputStreamReader(fileInputStream, charSet))));
            List<FieldDescriptorBean> fieldDescriptorBeanList = fetchFieldDescriptorList(type);
            String[] rowDataArr = null;
            while (null != (rowDataArr = csvParser.getLine())) {
                T bean = wrapBean(rowDataArr, fieldDescriptorBeanList, type);
                if (null != bean) {
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }

    static CsvReader getInstance() {
        return CsvReaderHolder.INSTANCE;
    }

    /**
     * 获取值
     *
     * @param sourceValue
     * @param fieldDescriptorBean
     * @return
     * @author wulang
     * @date 2018/1/16 20:33
     * @modify by user: {修改人}  2018/1/16 20:33
     * @modify by reason:
     */
    private Object fetchValue(String sourceValue, FieldDescriptorBean fieldDescriptorBean) {
        Object fieldValue = null;
        if (StringUtils.isNotBlank(sourceValue)) {
            FileField fileField = fieldDescriptorBean.getFileField();
            BaseFunction function = fileField.rRule().getFunction();
            fieldValue = function.apply(sourceValue, fieldDescriptorBean.getType());
        }
        return fieldValue;
    }

    /**
     * 包装bean
     *
     * @param rowDataArr
     * @param fieldDescriptorBeanList
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 20:33
     * @modify by user: {修改人}  2018/1/16 20:33
     * @modify by reason:
     */
    private <T> T wrapBean(String[] rowDataArr, List<FieldDescriptorBean> fieldDescriptorBeanList, Class<T> type) {
        T target = null;
        Method setMethod = null;
        try {
            target = type.newInstance();
            if (null != rowDataArr) {
                for (int i = 0; i < rowDataArr.length && i < fieldDescriptorBeanList.size(); i++) {
                    String value = rowDataArr[i];
                    FieldDescriptorBean fieldDescriptorBean = fieldDescriptorBeanList.get(i);
                    setMethod = fieldDescriptorBean.getSetMethod();
                    Object fieldValue = fetchValue(value, fieldDescriptorBean);
                    setMethod.invoke(target, fieldValue);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return target;
    }

    private static class CsvReaderHolder {
        private static final CsvReader INSTANCE = new CsvReader();
    }


}
