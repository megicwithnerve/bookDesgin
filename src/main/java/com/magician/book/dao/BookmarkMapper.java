package com.magician.book.dao;

import com.magician.book.pojo.Bookmark;

public interface BookmarkMapper {
    int deleteByPrimaryKey(Integer bookmarkId);

    int insert(Bookmark record);

    int insertSelective(Bookmark record);

    Bookmark selectByPrimaryKey(Integer bookmarkId);

    int updateByPrimaryKeySelective(Bookmark record);

    int updateByPrimaryKey(Bookmark record);
}