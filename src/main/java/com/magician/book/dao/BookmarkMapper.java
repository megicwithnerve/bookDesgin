package com.magician.book.dao;

import com.magician.book.pojo.Bookmark;

import java.util.List;

public interface BookmarkMapper {
    int deleteByPrimaryKey(Integer bookmarkId);

    int insert(Bookmark record);

    int insertSelective(Bookmark record);

    Bookmark selectByPrimaryKey(Integer bookmarkId);

    int updateByPrimaryKeySelective(Bookmark record);

    int updateByPrimaryKey(Bookmark record);

    //书签列表
    List<Bookmark> searchBookmark(Integer collectionId);
}