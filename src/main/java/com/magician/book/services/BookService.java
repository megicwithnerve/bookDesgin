package com.magician.book.services;



import com.magician.book.pojo.Book;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface BookService {

    //搜索
    public Pager searchBooks(Integer indexpage, Integer pagesize, String type, String bookname, String writername);
    //获取作者的书
    public Pager getWriterBooksById(Integer writerId,Integer indexpage,Integer pagesize);
    //添加新书
    public APIResult insertBook(Book book);

    //更新书籍信息 根据id
    public APIResult updateBook(Book book);

    //获取书籍信息
    public APIResult getBookById(Integer bookId);

    //获取推广书籍

    //
}
