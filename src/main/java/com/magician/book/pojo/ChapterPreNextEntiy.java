package com.magician.book.pojo;

import java.io.Serializable;

public class ChapterPreNextEntiy implements Serializable {
    Chapter chapter;
    Chapter pre;
    Chapter next;

    public Chapter getPre() {
        return pre;
    }

    public void setPre(Chapter pre) {
        this.pre = pre;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Chapter getNext() {
        return next;
    }

    public void setNext(Chapter next) {
        this.next = next;
    }
}
