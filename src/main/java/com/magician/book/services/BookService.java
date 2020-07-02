package com.magician.book.services;



import com.github.pagehelper.PageInfo;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.BookKind;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

import java.util.List;

public interface BookService {

    //搜索
    public Pager searchBooks( String type,Integer writerId, String bookname, String writername,Integer bookStatus,
                              Integer clicks,Integer thumbs,Integer indexpage, Integer pagesize);
    //获取作者的书
    public Pager getWriterBooksById(Integer writerId,Integer bookStatus,Integer indexpage,Integer pagesize);
    //添加新书
    public APIResult insertBook(Book book);

    //更新书籍信息 根据id
    public APIResult updateBook(Book book);

    //获取书籍信息
    public APIResult getBookById(Integer bookId);

    //获取推广书籍

    //前端获取书籍entity
    public Pager getBookEntity(Integer writerId,String searchKey,String bookKind,Integer chapterTime,Integer clicks,Integer thumbs,Integer indexpage,Integer pagesize);

    //获取分类信息
    public Pager getBookKindList(Integer offset,Integer limit);
}
