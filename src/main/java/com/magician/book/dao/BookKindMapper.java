package com.magician.book.dao;

import com.magician.book.pojo.BookKind;

import java.util.List;

public interface BookKindMapper {
//    int deleteByPrimaryKey(Integer bookkindId);
//
//    int insert(BookKind record);
//
//    int insertSelective(BookKind record);
//
//    BookKind selectByPrimaryKey(Integer bookkindId);
//
//    int updateByPrimaryKeySelective(BookKind record);
//
//    int updateByPrimaryKey(BookKind record);

    List<BookKind> getBookList(Integer offset,Integer limit);
}