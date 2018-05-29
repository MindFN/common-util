/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 19:19
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.csv;

import java.nio.charset.Charset;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 19:19
 * @description
 * @modified By:
 * @modifued reason:
 */
public class CsvConstant {
    /**
     * CSV默认分隔符号
     */
    public static final String CSV_DEFAULT_WORD_SEPARATE = ",";
    /**
     * 默认换行符号
     */
    public static final String CSV_DEFAULT_LINE_SEPARATE = "\r\n";
    /**
     * 默认编码
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
}
