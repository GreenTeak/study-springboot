package com.study.exception;

/**
 * 自定义异常 2020-03-16
 */
public class GenException extends Exception {

    public GenException() {
    }

    public GenException(String message) {
        super("业务异常：" + message);
    }
}
