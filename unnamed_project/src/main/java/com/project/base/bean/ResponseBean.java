package com.project.base.bean;

/**
 * Created by PC on 2020/6/10.
 */
public class ResponseBean {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseBean ok(String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMsg(msg);
        return responseBean;
    }
}
