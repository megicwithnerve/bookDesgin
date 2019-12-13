package com.magician.book.utils;

public class APIResult implements java.io.Serializable {



    private String message;
    private boolean result;
    private int code = 200;
    private Object data;

    public APIResult() {
       this(false);
    }

    public APIResult(boolean result) {
        this(result,WebCode.NO_DATA_FOUND);

    }

    public APIResult( boolean result, int code) {
       this(null,result,code);
    }


    public APIResult(String message, boolean result, int code) {
       this(message,result,code,null);
    }


    public APIResult(String message, boolean result, int code, Object data) {
        if(!result && code == 200){
            code = WebCode.NO_DATA_FOUND;
        }
        this.message = message;
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
