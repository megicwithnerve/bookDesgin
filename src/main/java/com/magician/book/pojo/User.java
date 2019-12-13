package com.magician.book.pojo;


public class User implements java.io.Serializable {

    private Integer id;
    public String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                '}';
    }
}
