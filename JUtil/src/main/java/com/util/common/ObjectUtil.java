package com.util.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * 对象工具
 *
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class ObjectUtil {
    /**
     * 检测对象为null
     * <pron>
     * -->对象不存在;对象为容器时size==0;对象为字符串时为"";
     * </pron>
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if (null != obj) {
            if (obj instanceof String) {
                return "".equals(obj);
            }
            if (obj instanceof Collection) {
                return ((Collection) obj).size() == 0;
            }
            if (obj instanceof Map) {
                return ((Map) obj).size() == 0;
            }
            return false;
        }
        return true;
    }

    /**
     * 将字符串转换为目标类型
     *
     * @param source
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:24
     * @modify by user: {修改人}  2017/12/1 10:24
     * @modify by reason:
     */
    public static <T> T transformStringToTargetType(String source, Class<T> clazz) {
        try {
            return clazz.getConstructor(String.class).newInstance(source);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 获得对象的String，null的情况下返回空字符串
     *
     * @param obj
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:25
     * @modify by user: {修改人}  2017/12/1 10:25
     * @modify by reason:
     */
    public static String String(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 判断指定值是否为null，为null的情况下返回替换值
     *
     * @param field
     * @param replace
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:28
     * @modify by user: {修改人}  2017/12/1 10:28
     * @modify by reason:
     */
    public static <T> T hasValue(T field, T replace) {
        return !isNull(field) ? field : replace;
    }

    /**
     * 判断是否为正整数
     *
     * @param number
     * @return
     */
    public static boolean isPostiveInteger(Number number) {
        if (!isNull(number)) {
            if (number instanceof Integer || number instanceof Long || number instanceof Short) {
                return !number.equals(0) && !number.toString().startsWith("-");
            }
        }
        return false;
    }

    /**
     * 判断为负数
     *
     * @param number
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:26
     * @modify by user: {修改人}  2017/12/1 10:26
     * @modify by reason:
     */
    public static boolean isNegativeNum(Number number) {
        return !isPostiveInteger(number);
    }

    public static String AsGetMethodName(String name, boolean isGet) {
        return (isGet ? "get" : "set") + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

}
