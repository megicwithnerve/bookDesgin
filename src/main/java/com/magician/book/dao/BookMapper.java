package com.magician.book.dao;

import com.magician.book.pojo.Book;
import io.swagger.models.auth.In;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    //书籍列表
    List<Book> searchBooks(String kind, Integer writerId,String bookname, String writername,Integer bookStatus,Integer clicks,Integer thumbs,Integer offset,Integer limit);
    //根据id获取
    List<Book> getWriterBooksById(Integer writerId, Integer bookStatus,Integer offset,Integer limit);
}