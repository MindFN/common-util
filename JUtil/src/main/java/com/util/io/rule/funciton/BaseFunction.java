/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 20:45
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.rule.funciton;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 20:45
 * @description
 * @modified By:
 * @modifued reason:
 */
public interface BaseFunction<F> {
    Object apply(F f, Class sourceType);

    Object apply(F f);
}
