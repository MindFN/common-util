package com.util.entity;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.util.collection.Collection7Utils;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * bean对象的字段控制工具，不包含父对象
 *
 * @Author: wulang
 * @Date: 2017年10月09日 17:32
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class BeanUtil {
    /**
     * 序列化表示字段
     */
    public static final String SERIALIZE_FIELD_NAME = "serialVersionUID";

    /**
     * 获取所有的读方法
     *
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/10/9 18:48
     * @modify by user: {修改人}  2017/10/9 18:48
     * @modify by reason:
     */
    public static <T> List<Method> listGetMethod(Class<T> clazz) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz);
        return Lists.transform(propertyDescriptorList, new Function<PropertyDescriptor, Method>() {
            @Override
            public Method apply(PropertyDescriptor input) {
                return input.getReadMethod();
            }
        });
    }

    /**
     * 获取所有的读方法 使用predicate过滤
     *
     * @param clazz
     * @param predicate
     * @return
     * @author: wulang
     * @date: 2017/10/9 18:49
     * @modify by user: {修改人}  2017/10/9 18:49
     * @modify by reason:
     */
    public static <T> List<Method> listGetMethod(Class<T> clazz, Predicate<PropertyDescriptor> predicate) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz);
        propertyDescriptorList = Lists.newArrayList(Collections2.filter(propertyDescriptorList, predicate));
        return Lists.transform(propertyDescriptorList, new Function<PropertyDescriptor, Method>() {
            @Override
            public Method apply(PropertyDescriptor input) {
                return input.getReadMethod();
            }
        });
    }

    /**
     * 所有的写方法
     *
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/10/9 18:55
     * @modify by user: {修改人}  2017/10/9 18:55
     * @modify by reason:
     */
    public static <T> List<Method> listSetMethod(Class<T> clazz) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz);
        return Lists.transform(propertyDescriptorList, new Function<PropertyDescriptor, Method>() {
            @Override
            public Method apply(PropertyDescriptor input) {
                return input.getWriteMethod();
            }
        });
    }

    /**
     * 获取所有的写方法 使用predicate过滤
     *
     * @param clazz
     * @param predicate
     * @return
     * @author: wulang
     * @date: 2017/10/9 18:56
     * @modify by user: {修改人}  2017/10/9 18:56
     * @modify by reason:
     */
    public static <T> List<Method> listSetMethod(Class<T> clazz, Predicate<PropertyDescriptor> predicate) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz, predicate);
        return Lists.transform(propertyDescriptorList, new Function<PropertyDescriptor, Method>() {
            @Override
            public Method apply(PropertyDescriptor input) {
                return input.getWriteMethod();
            }
        });
    }

    public static <T, A> Map<Method, A> mapSetMethod(Class<T> clazz, Predicate<PropertyDescriptor> predicate, Function<PropertyDescriptor, Map.Entry<Method, A>> function) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz, predicate);
        return Collection7Utils.transformCollectionToMap(propertyDescriptorList, function);
    }

    public static <T> List<PropertyDescriptor> listProperties(Class<T> clazz) {
        PropertyDescriptor propertyDescriptor = null;
        List<Field> fieldList = listFields(clazz);
        List<PropertyDescriptor> propertyDescriptorList = Lists.newArrayList();
        for (Field field : fieldList) {
            try {
                if (!StringUtils.equals(SERIALIZE_FIELD_NAME, field.getName())) {
                    propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
                    propertyDescriptorList.add(propertyDescriptor);
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        return propertyDescriptorList;
    }

    /**
     * 获取所有的properties
     *
     * @param clazz
     * @param predicate
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:25
     * @modify by user: {修改人}  2017/12/1 11:25
     * @modify by reason:
     */
    public static <T> List<PropertyDescriptor> listProperties(Class<T> clazz, Predicate<PropertyDescriptor> predicate) {
        List<PropertyDescriptor> propertyDescriptorList = listProperties(clazz);
        return Lists.newArrayList(Collections2.filter(propertyDescriptorList, predicate));
    }

    /**
     * 获取所有字段
     *
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:21
     * @modify by user: {修改人}  2017/12/1 11:21
     * @modify by reason:
     */
    public static <T> List<Field> listFields(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Lists.newArrayList(fields);
    }

    /**
     * 根据指定规则过滤字段
     *
     * @param clazz
     * @param predicate
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:18
     * @modify by user: {修改人}  2017/12/1 11:18
     * @modify by reason:
     */
    public static <T> List<Field> listFields(Class<T> clazz, Predicate<Field> predicate) {
        List<Field> fieldList = listFields(clazz);
        if (null != predicate) {
            fieldList = Lists.newArrayList(Collections2.filter(fieldList, predicate));
        }
        return fieldList;
    }

}
