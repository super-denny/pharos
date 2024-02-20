package com.pharos.common.response;


import lombok.Data;

import java.io.Serializable;


@Data
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Response<T> success() {
        this.code = BaseCode.SUCCESS.getCode();
        this.msg = BaseCode.SUCCESS.getInfo();
        return this;
    }

    public Response<T> success(T data) {
        this.code = BaseCode.SUCCESS.getCode();
        this.msg = BaseCode.SUCCESS.getInfo();
        this.data = data;
        return this;
    }

    public Response<T> error(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.getInfo();
        this.data = data;
        return this;
    }

    public Response<T> error(Code code) {
        this.code = code.getCode();
        this.msg = code.getInfo();
        this.data = null;
        return this;
    }

    public Response<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
        return this;
    }

}
