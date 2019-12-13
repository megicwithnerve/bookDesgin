package com.magician.book.utils;

import java.util.Date;

public class LoginToken implements java.io.Serializable{

    private UserType userType;//token类型
    private Object obj;//用户信息
    private Long date;//过期时间

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Long getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "LoginToken{" +
                "userType=" + userType +
                ",obj=" + obj +
                ",date=" + date +
                '}';
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
