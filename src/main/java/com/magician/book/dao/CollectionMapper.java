package com.magician.book.dao;

import com.magician.book.pojo.BookCollection;
import com.magician.book.pojo.BookCollectionEntity;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(BookCollection record);

    int insertSelective(BookCollection record);

    BookCollection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(BookCollection record);

    int updateByPrimaryKey(BookCollection record);

    //获取书架列表
    List<BookCollectionEntity> getCollectionList(Integer readerId, Integer offset, Integer limit);

    //获取一个
    BookCollection getCollectionByReadIdBookId(Integer readerId,Integer bookId);
}