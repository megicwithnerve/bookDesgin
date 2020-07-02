package com.magician.book.pojo;

import java.util.Date;

public class BuyrecordEntityAll {
    private Integer buyrecordId;

    private Reader reader;

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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
}
