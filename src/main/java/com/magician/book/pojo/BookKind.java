package com.magician.book.pojo;

public class BookKind {
    private Integer bookkindId;

    private String bookkindname;

    public Integer getBookkindId() {
        return bookkindId;
    }

    public void setBookkindId(Integer bookkindId) {
        this.bookkindId = bookkindId;
    }

    public String getBookkindname() {
        return bookkindname;
    }

    public void setBookkindname(String bookkindname) {
        this.bookkindname = bookkindname == null ? null : bookkindname.trim();
    }
}