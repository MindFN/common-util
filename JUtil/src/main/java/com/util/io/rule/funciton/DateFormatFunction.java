/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 20:56
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.rule.funciton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 20:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class DateFormatFunction implements BaseFunction<Date> {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object apply(Date date, Class sourceType) {
        String dateStr = "";
        if (null != date) {
            dateStr = dateFormat.format(date);
        }
        return dateStr;
    }

    @Override
    public Object apply(Date date) {
        return apply(date, null);
    }
}
