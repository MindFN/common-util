package com.util.date;

import com.util.common.ObjectUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具
 *
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class DateFormatUtil {
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认格式化对象
     */
    private static DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DEFAULT_PATTERN);

    /**
     * 使用指定模式 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:41
     * @modify by user: {修改人}  2017/12/1 10:41
     * @modify by reason:
     */
    public static String formateDate(Date date, String pattern) {
        String dateStr = "";
        if (ObjectUtil.isNull(pattern)) {
            dateStr = formateDate(date);
        } else if (!ObjectUtil.isNull(date)) {
            dateStr = new SimpleDateFormat(pattern).format(date);
        }
        return dateStr;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:42
     * @modify by user: {修改人}  2017/12/1 10:42
     * @modify by reason:
     */
    public static String formateDate(Date date) {
        if (!ObjectUtil.isNull(date)) {
            return SIMPLE_DATE_FORMAT.format(date);
        }
        return "";
    }

    /**
     * 使用指定格式解析，转换String为日期
     *
     * @param date
     * @param pattern
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:42
     * @modify by user: {修改人}  2017/12/1 10:42
     * @modify by reason:
     */
    public static Date parseDate(String date, String pattern) {
        Date tDate = null;
        try {
            if (ObjectUtil.isNull(pattern)) {
                tDate = parseDate(date);
            } else if (!ObjectUtil.isNull(date)) {
                tDate = new SimpleDateFormat(pattern).parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tDate;
    }

    /**
     * 使用默认格式，转化日期
     *
     * @param date
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:42
     * @modify by user: {修改人}  2017/12/1 10:42
     * @modify by reason:
     */
    public static Date parseDate(String date) {
        try {
            if (!ObjectUtil.isNull(date)) {
                return SIMPLE_DATE_FORMAT.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串日期转为sqlDate
     * @author: wulang
     * @date: 2017/12/1 10:43
     * @param date
     * @modify by user: {修改人}  2017/12/1 10:43
     * @modify by reason:
     * @return
     */
    public static java.sql.Date parseSQLDate(String date) {
        try {
            if (ObjectUtil.isNull(date)) {
                return new java.sql.Date(SIMPLE_DATE_FORMAT.parse(date).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转化UtilDate--》SQLDate
     *
     * @param date
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:43
     * @modify by user: {修改人}  2017/12/1 10:43
     * @modify by reason:
     */
    public static java.sql.Date transferJavaDateToUtilDate(Date date) {
        if (ObjectUtil.isNull(date)) {
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    /**
     * 转化SQLDate--》UtilDate
     *
     * @param date
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:44
     * @modify by user: {修改人}  2017/12/1 10:44
     * @modify by reason:
     */
    public static Date transferSQLDateToUtilDate(java.sql.Date date) {
        if (ObjectUtil.isNull(date)) {
            return new Date(date.getTime());
        }
        return null;
    }

    /**
     * 转化UtilDate--》SQLTime
     * @author: wulang
     * @date: 2017/12/1 10:44
     * @param date
     * @modify by user: {修改人}  2017/12/1 10:44
     * @modify by reason:
     * @return
     */
    public static java.sql.Timestamp transferJavaDateToSQLTimestamp(Date date) {
        if (ObjectUtil.isNull(date)) {
            return new java.sql.Timestamp(date.getTime());
        }
        return null;
    }

    /**
     * 转化SQLTime到UtilDate
     *
     * @param time
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:45
     * @modify by user: {修改人}  2017/12/1 10:45
     * @modify by reason:
     */
    public static Date transferSQLTimestampToUtilDate(java.sql.Timestamp time) {
        if (ObjectUtil.isNull(time)) {
            return new Date(time.getTime());
        }
        return null;
    }
}
