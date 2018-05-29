package com.util.io.file.exception;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年12月12日 11:33
 * @description
 * @modified By:
 * @modifued reason:
 */
public class FileException extends RuntimeException {
    private int code;
    private String message;

    public FileException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
