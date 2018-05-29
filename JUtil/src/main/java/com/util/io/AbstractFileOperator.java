package com.util.io;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.util.io.annotation.FileBean;
import com.util.io.annotation.FileField;
import com.util.io.rule.FieldPredicate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: wulang
 * @Date: 2017年10月14日 15:50
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public abstract class AbstractFileOperator {

    /**
     * 获取对象所有属性的描述类
     *
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/10/14 11:36
     * @modify by user: {修改人}  2017/10/14 11:36
     * @modify by reason:
     */
    protected <T> List<FieldDescriptorBean> fetchFieldDescriptorList(Class<T> type) {
        PropertyDescriptor propertyDescriptor = null;
        FieldDescriptorBean fieldDescriptor = null;
        boolean isXLSBeanPresent = isFileBeanPresent(type);
        List<Field> fieldList = fetchFields(type, isXLSBeanPresent);
        List<FieldDescriptorBean> fieldDescriptorBeanList = Lists.newArrayListWithCapacity(fieldList.size());
        for (Field field : fieldList) {
            try {
                propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                fieldDescriptor = new FieldDescriptorBean();
                fieldDescriptor.setField(field);
                fieldDescriptor.setType(field.getType());
                fieldDescriptor.setFileField(field.getAnnotation(FileField.class));
                fieldDescriptor.setSetMethod(propertyDescriptor.getWriteMethod());
                fieldDescriptor.setGetMethod(propertyDescriptor.getReadMethod());
                fieldDescriptorBeanList.add(fieldDescriptor);
            } catch (IntrospectionException e) {
//                LogUtils.logException(e);
            }
        }
        if (isXLSBeanPresent) {
            Collections.sort(fieldDescriptorBeanList, new Comparator<FieldDescriptorBean>() {
                @Override
                public int compare(FieldDescriptorBean o1, FieldDescriptorBean o2) {
                    return o1.getFileField().index() - o2.getFileField().index();
                }
            });
        }
        return fieldDescriptorBeanList;
    }

    /**
     * 检测xlsBEAN是否存在
     *
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/12/26 15:27
     * @modify by user: {修改人}  2017/12/26 15:27
     * @modify by reason:
     */
    private <T> boolean isFileBeanPresent(Class<T> type) {
        boolean isFileBeanPresent = false;
        Class<?> superClazz = type;
        do {
            isFileBeanPresent = superClazz.isAnnotationPresent(FileBean.class);
            superClazz = superClazz.getSuperclass();
        } while (!superClazz.equals(Object.class) && !isFileBeanPresent);
        return isFileBeanPresent;
    }

    /**
     * 获取所有的字段
     *
     * @param type
     * @return
     * @author: wulang
     * @date: 2017/12/26 15:26
     * @modify by user: {修改人}  2017/12/26 15:26
     * @modify by reason:
     */
    private <T> List<Field> fetchFields(Class<T> type, boolean isFileBeanPresent) {
        List<Field> fieldList = Lists.newArrayList();
        Class<?> superClazz = type;
        if (!isFileBeanPresent) {
            return Arrays.asList(type.getDeclaredFields());
        } else {
            while (!superClazz.isAnnotationPresent(FileBean.class)) {
                fieldList.addAll(Collections2.filter(Arrays.asList(superClazz.getDeclaredFields()), FieldPredicate.getInstance()));
                superClazz = superClazz.getSuperclass();
            }
            //补充FileBean的所有field
            fieldList.addAll(Collections2.filter(Arrays.asList(superClazz.getDeclaredFields()), FieldPredicate.getInstance()));
        }
        return fieldList;
    }

    /**
     * @Author: wulang
     * @Date: 2017年10月14日 15:27
     * @Version: v1.0
     * @Description:
     * @Modified By:
     * @Modifued reason
     */
    public class FieldDescriptorBean {
        private Class<?> type;
        private FileField fileField;
        private Field field;
        private Method setMethod;
        private Method getMethod;

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
        }

        public FileField getFileField() {
            return fileField;
        }

        public void setFileField(FileField fileField) {
            this.fileField = fileField;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public Method getSetMethod() {
            return setMethod;
        }

        public void setSetMethod(Method setMethod) {
            this.setMethod = setMethod;
        }

        public Method getGetMethod() {
            return getMethod;
        }

        public void setGetMethod(Method getMethod) {
            this.getMethod = getMethod;
        }

    }

}
