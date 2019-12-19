package com.magician.book.dao;

import com.magician.book.pojo.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> searchBooks(String kind, String bookname, String writername);

    List<Book> getWriterBooksById(Integer writerId);
}