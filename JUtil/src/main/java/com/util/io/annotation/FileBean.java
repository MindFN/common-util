package com.util.io.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于标识对象是否使用注解的方式进行的解析
 *
 * @Author: wulang
 * @Date: 2017年10月14日 10:42
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
public @interface FileBean {
    /**
     * 对导出文件的title进行国际化转换
     * @author wulang
     * @date 2018/1/20 10:53
     * @modify by user: {修改人}  2018/1/20 10:53
     * @modify by reason:
     * @return boolean
     */
    boolean transform() default false;
}
