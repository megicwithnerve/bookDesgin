package com.magician.book.services.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.dao.BookMapper;
import com.magician.book.pojo.Book;
import com.magician.book.services.BookService;
import com.magician.book.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public PageInfo searchBooks(Integer indexpage, Integer pagesize, String type, String bookname, String writername) {
        PageHelper.startPage(indexpage,pagesize);
        List<Book> list = bookMapper.searchBooks(type,bookname,writername);
        PageInfo pageInfo = new PageInfo(list,5);
        return pageInfo;
    }

    @Override
    public PageInfo getWriterBooksById(Integer writerId, Integer indexpage, Integer pagesize) {

        PageHelper.startPage(indexpage,pagesize);
        List<Book> list = bookMapper.getWriterBooksById(writerId);
        PageInfo pageInfo = new PageInfo(list,5);
        return pageInfo;
    }

    @Override
    public APIResult insertBook(Book book) {
        APIResult apiResult = null;
        Integer result = bookMapper.insertSelective(book);
        if(result == 0){
            apiResult = new APIResult("插入失败",false,500,null);
        }else {
            apiResult = new APIResult("插入成功，请等待审核",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult updateBook(Book book) {
        APIResult apiResult = null;
        Integer result = bookMapper.updateByPrimaryKeySelective(book);
        if(result == 0){
            apiResult = new APIResult("更新失败",false,500,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult getBookById(Integer bookId) {
        APIResult apiResult = null;
        Book result = bookMapper.selectByPrimaryKey(bookId);
        if(result == null){
            apiResult = new APIResult("获取失败",false,500,null);
        }else {
            apiResult = new APIResult("",true,200,result);
        }
        return apiResult;
    }
}
