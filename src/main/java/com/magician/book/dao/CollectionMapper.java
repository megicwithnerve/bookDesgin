package com.magician.book.dao;

import com.magician.book.pojo.BookCollection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(BookCollection record);

    int insertSelective(BookCollection record);

    BookCollection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(BookCollection record);

    int updateByPrimaryKey(BookCollection record);
}