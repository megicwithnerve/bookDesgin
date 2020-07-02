package com.magician.book.utils;

public enum RechargeEnum {

    CANCELED(0,"待支付"),
    ORDER_SUCCESS(1,"支付成功"),
    ORDER_CLOSE(2,"支付失效");

    RechargeEnum(int code,String value){
        this.code = code;
        this.value = value;
    };

    private String value;
    private int code;

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static RechargeEnum getRechargeEnum(int code){

        for (RechargeEnum r: values()) {
            if(r.getCode() == code){
                return r;
            }
        }

        throw new RuntimeException("");
    }
}
