package com.util.io.annotation;


import com.util.io.rule.Rule;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于解析使用的对象字段注解
 *
 * @Author: wulang
 * @Date: 2017年10月09日 16:48
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */

@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface FileField {
    /**
     * 字段名
     *
     * @return
     */
    String name();

    /**
     * 字段顺序
     */
    int index() default 0;

    /**
     * 默认值
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * 写出时字段规则，重新格式化改值，改类型，注意类型发生变化时，需要和newType一致
     *
     * @return
     */
    Rule wRule() default Rule.DEFAULT_FUNCTION;

    /**
     * 读取时格式化规则
     *
     * @return
     */
    Rule rRule() default Rule.DEFAULT_FUNCTION;

    /**
     * 对filed取到的值进行国际化转换，默认不转换
     *
     * @return
     */
    boolean transform() default false;

    /**
     * 转换之后的目标类型
     *
     * @return
     */
    Class<?> newType() default Object.class;
}

