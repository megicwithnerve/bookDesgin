package com.magician.book.dao;

import com.magician.book.pojo.Comment;
import com.magician.book.pojo.CommentEntity;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentEntity> selectCommentEntity(Integer bookId,Integer offset,Integer limit);
}