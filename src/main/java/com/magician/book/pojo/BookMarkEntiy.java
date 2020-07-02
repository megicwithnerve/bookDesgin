package com.magician.book.pojo;

/**
 * 书签实体
 */
public class BookMarkEntiy implements java.io.Serializable{
    private Integer bookmarkId;

    private Integer collectionId;

    private Chapter chapter;

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Integer getBookCollectionId() {
        return collectionId;
    }

    public void setBookCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
