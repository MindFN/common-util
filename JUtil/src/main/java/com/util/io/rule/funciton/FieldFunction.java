package com.util.io.rule.funciton;

import com.google.common.base.Function;
import com.util.collection.Collection7Utils;
import com.util.io.annotation.FileField;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: wulang
 * @Date: 2017年10月09日 20:03
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class FieldFunction<S> implements Function<PropertyDescriptor, Map.Entry<Method, FileField>> {
    private final Class<S> clazz;

    public FieldFunction(Class<S> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Map.Entry<Method, FileField> apply(PropertyDescriptor input) {
        FileField anno = null;
        Method method = null;
        try {
            anno = clazz.getDeclaredField(input.getName()).getAnnotation(FileField.class);
            method = input.getReadMethod();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return new Collection7Utils.MEntry<>(method, anno);
    }
}
