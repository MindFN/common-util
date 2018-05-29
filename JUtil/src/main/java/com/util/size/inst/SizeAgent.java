package com.util.size.inst;

import com.util.common.ObjectUtil;


import java.beans.PropertyDescriptor;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @Author: wulang
 * @Date: 2017年10月17日 13:03
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class SizeAgent {
    private static Instrumentation inst;

    public static void permain(String args, Instrumentation instrumentation) {
        inst = instrumentation;
    }

    public static long sizeOf(Object object, Class<?> type) {
        return loopCount(object, type, 0);
    }

    private static long loopCount(Object object, Class<?> type, long cSize) {
        //获取目标对象的shallowSize
        cSize += inst.getObjectSize(object);
        PropertyDescriptor propertyDescriptor = null;
        Class<?> fType = null;
        Object value = null;
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                value = field.get(object);
                if(ObjectUtil.isNull(value)){
                    continue;
                }
                //遍历属性，获取其引用类型属性对象的大小
                type = field.getType();
                if (Object.class.isAssignableFrom(field.getType())) {
                    cSize += loopCount(value, field.getType(), cSize);
                }
                //属性为数组时，且其中元素类型为引用类型时，获取其大小
                type = type.getComponentType();
                if (!ObjectUtil.isNull(type)&&Object.class.isAssignableFrom(type)) {
                    int length = Array.getLength(value);
                    for (int i = 0; i < length; i++) {
                        value = Array.get(object, i);
                        cSize += loopCount(value, type, cSize);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return cSize;
    }

}
