package com.project.base.exception;

/**
 * Created by 小白 on 2020/5/31.
 */
public class ExceptionResponse {

    private int code;

    private String msg;

    ExceptionResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
