package com.util.io.file.exception;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年12月12日 11:34
 * @description
 * @modified By:
 * @modifued reason:
 */
public class ExceptionConstant {
    /**
     * 文件不存在
     */
    public static final int FILE_NOT_EXISTS = 1001;
    /**
     * 不能删
     */
    public static final int FILE_COULD_NOT_DELETE = 1002;
    /**
     * 不能读
     */
    public static final int FILE_COUNT_NOT_READ = 1003;
    /**
     * 不能写
     */
    public static final int FILE_COUNT_NOT_WRITE = 1004;
    /**
     * 不能改
     */
    public static final int FILE_COUNT_NOT_MODIFY = 1005;

    /**
     * 错误消息
     */
    public static final Map<Integer, String> ERROR_MESSAGE = Maps.newHashMap();

    /**
     * 文件夹不存在
     */
    public static final int DIR_NOT_EXISTS = 2001;


    static {
        ERROR_MESSAGE.put(FILE_NOT_EXISTS, "文件[{0}]不存在!");
        ERROR_MESSAGE.put(DIR_NOT_EXISTS, "文件目录[{0}]不存在!");
        ERROR_MESSAGE.put(FILE_COULD_NOT_DELETE, "文件[{0}]删除失败!");
        ERROR_MESSAGE.put(FILE_COUNT_NOT_READ, "文件[{0}]不能打开!");
        ERROR_MESSAGE.put(FILE_COUNT_NOT_WRITE, "文件[{0}]不能写入!");
        ERROR_MESSAGE.put(FILE_COUNT_NOT_MODIFY, "文件[{0}]不能修改!");
    }


}
