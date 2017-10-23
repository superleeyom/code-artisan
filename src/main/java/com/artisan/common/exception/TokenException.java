package com.artisan.common.exception;

/**
 * Token过期时抛出异常
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;

    public TokenException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}