package com.project.base.exception;

/**
 * Created by 小白 on 2020/5/31.
 */
public class GlobalException extends RuntimeException {

    private int code; //异常码

    public GlobalException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
