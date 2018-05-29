/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 20:34
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.rule.funciton;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 20:34
 * @description
 * @modified By:
 * @modifued reason:
 */
public class BasicTypeFunction implements BaseFunction<String> {

    @Override
    public Object apply(String source, Class sourceType) {
        Object target = null;
        if (null != source) {
            if (sourceType == Byte.class) {
                target = Byte.valueOf(source);
            } else if (sourceType == Short.class) {
                target = Short.valueOf(source);
            } else if (sourceType == Integer.class) {
                target = Integer.valueOf(source);
            } else if (sourceType == Long.class) {
                target = Long.valueOf(source);
            } else if (sourceType == Float.class) {
                target = Float.valueOf(source);
            } else if (sourceType == Double.class) {
                target = Double.valueOf(source);
            } else if (sourceType == Character.class) {
                target = source.length() > 0 ? source.charAt(0) : null;
            } else if (sourceType == Boolean.class) {
                target = Boolean.valueOf(source);
            }
        }
        return target;
    }

    @Override
    public Object apply(String s) {
        return s;
    }
}
