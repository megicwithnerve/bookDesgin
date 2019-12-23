package com.magician.book.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmailEntity implements Serializable {

    private Timestamp time;
    private String acount;
    private String email;

    @Override
    public String toString() {
        return "EmailEntity{" +
                "time=" + time +
                ", acount='" + acount + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
