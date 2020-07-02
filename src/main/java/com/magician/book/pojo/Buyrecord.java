package com.magician.book.pojo;

import java.util.Date;

public class Buyrecord {

    private Integer buyrecordId;

    private Integer readerId;

    private Integer bookId;

    private Integer chapterId;

    private Integer writerId;

    private Integer price;

    private Date createdtime;

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getBuyrecordId() {
        return buyrecordId;
    }

    public void setBuyrecordId(Integer buyrecordId) {
        this.buyrecordId = buyrecordId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}