package com.util.io.rule.funciton;


/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月02日 14:09
 * @description
 * @modified By:
 * @modifued reason:
 */
public class DefaultFunction implements BaseFunction {
    @Override
    public Object apply(Object object, Class sourceType) {
        return object;
    }

    @Override
    public Object apply(Object object) {
        return apply(object, null);
    }
}
