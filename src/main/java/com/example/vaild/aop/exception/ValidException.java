package com.example.vaild.aop.exception;

/**
 * @author dengliqiang
 * @dercription:
 * @date 2019-06-26 15:04
 */
public class ValidException  extends RuntimeException {
    private static final long serialVersionUID = -4224510023425266729L;

    private Integer code;

    public ValidException() {

    }

    public ValidException(String message) {
        super(message);
    }

    public ValidException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

}
