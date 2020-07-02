package com.magician.book.services;

import com.magician.book.pojo.Comment;
//import com.magician.book.utils.APIRequest;
//import com.magician.book.utils.Pager;

import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface CommentServices {

    //获取书籍评论
    public Pager getBookComment(Integer bookId, Integer indexpage, Integer pagesize);
//
    //删除评论
    public APIResult delBookComment(Integer bookId, Integer commentId, Integer writerId, Integer type);

    //增加评论
    public APIResult insertBookComment(Comment comment);
}
