package com.magician.book.pojo;

public class Reader {
    private Integer redaerId;

    private String email;

    private String password;

    private String username;

    private String telphone;

    private String balance;

    private String readerStatus;

    public Integer getRedaerId() {
        return redaerId;
    }

    public void setRedaerId(Integer redaerId) {
        this.redaerId = redaerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public String getReaderStatus() {
        return readerStatus;
    }

    public void setReaderStatus(String readerStatus) {
        this.readerStatus = readerStatus == null ? null : readerStatus.trim();
    }
}