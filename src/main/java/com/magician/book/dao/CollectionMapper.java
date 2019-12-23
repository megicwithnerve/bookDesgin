package com.magician.book.dao;

import com.magician.book.pojo.BookCollection;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(BookCollection record);

    int insertSelective(BookCollection record);

    BookCollection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(BookCollection record);

    int updateByPrimaryKey(BookCollection record);

    //获取书架列表
    List<BookCollection> getCollectionList(Integer readerId,Integer offset,Integer limit);

}