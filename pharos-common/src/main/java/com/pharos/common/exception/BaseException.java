package com.pharos.common.exception;


import com.pharos.common.response.Code;

public class BaseException extends RuntimeException {
    private int code;
    private String msg;

    public BaseException(Code code) {
        super(code.getFixTips());
        this.code = code.getCode();
        this.msg = code.getFixTips();
    }

    public BaseException(Code code, Throwable e) {
        super(code.getFixTips(), e);
        this.code = code.getCode();
        this.msg = code.getFixTips();
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
