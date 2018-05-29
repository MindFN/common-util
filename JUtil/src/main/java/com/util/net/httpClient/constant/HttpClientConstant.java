package com.util.net.httpClient.constant;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月10日 14:50
 * @description
 * @modified By:
 * @modifued reason:
 */
public class HttpClientConstant {
    // 连接超时
    protected static final Integer DEFAULT_CONNECTION_TIME_OUT = 2000;

    // 读取超时
    protected static final Integer DEFAULT_SOCKET_TIME_OUT = 5000;

    //最大读取超时时间
    protected static final Integer MAX_SOCKET_TIME_OUT = 100000;


    protected static final String DEFAULT_CONTENT_TYPE = "application/json";

    public static final String XML_CONTENT_TYPE = "application/xml";

    protected static final String DEFAULT_CHAR_SET = "UTF-8";
}
