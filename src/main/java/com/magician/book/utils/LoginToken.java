package com.magician.book.utils;

import java.util.Date;

public class LoginToken implements java.io.Serializable{

    private UserType userType;//token类型
    private Long objid;//用户信息
    private Long date;//过期时间
    private String objPassworld;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getObjid() {
        return objid;
    }

    public void setObjid(Long objid) {
        this.objid = objid;
    }

    public Long getDate() {
        return date;
    }


    public void setDate(Long date) {
        this.date = date;
    }

    public String getObjPassworld() {
        return objPassworld;
    }

    public void setObjPassworld(String objPassworld) {
        this.objPassworld = objPassworld;
    }
}
