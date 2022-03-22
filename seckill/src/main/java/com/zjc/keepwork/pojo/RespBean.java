package com.zjc.keepwork.pojo;

public class RespBean {
    private Integer Code;
    private String Message;
    private  Object Obj;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        this.Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public Object getObj() {
        return Obj;
    }

    public void setObj(String obj) {
        this.Obj = obj;
    }
}
