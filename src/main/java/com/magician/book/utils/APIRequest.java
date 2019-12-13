package com.magician.book.utils;

public class APIRequest implements java.io.Serializable {


    public APIRequest() {
    }


    public APIRequest(Object data) {
        this.data = data;
    }

    private boolean result;

    private String msg;

    private Object data;

    public APIRequest(boolean result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public APIRequest(boolean result) {
        this.result = result;
    }

    public APIRequest(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
