package com.magician.book.pojo;

import java.util.Date;

public class Thumb {
    private Integer thumbId;

    private Integer bookId;

    private Integer readerId;

    private Integer thumbStatus;

    private Date updatetime;

    public Integer getThumbId() {
        return thumbId;
    }

    public void setThumbId(Integer thumbId) {
        this.thumbId = thumbId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getThumbStatus() {
        return thumbStatus;
    }

    public void setThumbStatus(Integer thumbStatus) {
        this.thumbStatus = thumbStatus;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}