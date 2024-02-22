package com.pharos.common.exception;


import com.pharos.common.response.BaseCode;
import com.pharos.common.response.Code;

public class BizException extends BaseException {

    public BizException(Code code) {
        super(code);
    }

    public BizException(String msg) {
        super(BaseCode.SYSTEM_FAILED.getCode(), msg);
    }

    public BizException(Code code, Throwable e) {
        super(code, e);
    }

    public BizException(int code, String msg) {
        super(code, msg);
    }

    public BizException(int code, String msg, Throwable e) {
        super(code, msg, e);
    }
}
