package com.magician.book.pojo;

/**
 * 书签实体
 */
public class BookMarkEntiy implements java.io.Serializable{
    private Integer bookmarkId;

    private BookCollection bookCollection;

    private Chapter chapter;

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public BookCollection getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
