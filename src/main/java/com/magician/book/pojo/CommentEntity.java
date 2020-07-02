package com.magician.book.pojo;

import java.util.Date;
import java.util.List;

public class CommentEntity {

    private Integer commentId;

    private Integer bookId;

    private Reader reader;

    private Integer parentId;

    private Date createtime;

    private List<CommentEntity> backComment;


    private String context;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<CommentEntity> getBackComment() {
        return backComment;
    }

    public void setBackComment(List<CommentEntity> backComment) {
        this.backComment = backComment;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
