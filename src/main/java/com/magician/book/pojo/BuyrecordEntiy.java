package com.magician.book.pojo;

import java.io.Serializable;
import java.util.Date;

public class BuyrecordEntiy implements Serializable {

    private Integer buyrecordId;

    private Book book;

    private Chapter chapter;

    private Writer writer;

    private Integer price;

    private Date createdtime;

    public Integer getBuyrecordId() {
        return buyrecordId;
    }

    public void setBuyrecordId(Integer buyrecordId) {
        this.buyrecordId = buyrecordId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
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

    @Override
    public String toString() {
        return "BuyrecordEntiy{" +
                "buyrecordId=" + buyrecordId +
                ", book=" + book +
                ", chapter=" + chapter +
                ", writer=" + writer +
                ", price=" + price +
                ", createdtime=" + createdtime +
                '}';
    }
}
