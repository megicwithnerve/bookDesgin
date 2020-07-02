package com.magician.book.services.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.dao.BookEntityMapper;
import com.magician.book.dao.BookKindMapper;
import com.magician.book.dao.BookMapper;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.BookEntity;
import com.magician.book.pojo.BookKind;
import com.magician.book.services.BookService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    BookEntityMapper bookEntityMapper;

    @Resource
    BookKindMapper bookKindMapper;

    @Override
    public Pager searchBooks( String type, Integer writerId,String bookname, String writername,Integer bookStatus,
                              Integer clicks,Integer thumbs,Integer indexpage, Integer pagesize) {

        if(type.equals("")){
            type = null;
        }

        if(bookname.equals("")){
            bookname = null;
        }else {
            bookname+="%";
        }
        if(writername.equals("")){
            writername = null;
        }else {
            writername+="%";
        }
        List<Book> list = bookMapper.searchBooks(type,writerId,bookname,writername,bookStatus,clicks,thumbs,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Book> books = bookMapper.searchBooks(type,writerId,bookname,writername,bookStatus,clicks,thumbs,pager.getBeginrows(),pagesize);
        pager.setData(books);
        return pager;
    }

    @Override
    public Pager getWriterBooksById(Integer writerId,Integer bookStatus, Integer indexpage, Integer pagesize) {

        List<Book> list = bookMapper.getWriterBooksById(writerId,bookStatus,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Book> books = bookMapper.getWriterBooksById(writerId,bookStatus,pager.getBeginrows(),pagesize);
        pager.setData(books);
        return pager;
    }

    @Override
    public APIResult insertBook(Book book) {
        APIResult apiResult = null;
        book.setBookStatus(0);
        book.setClicks(0);
        book.setCreatetime(new Date());
        book.setThumbs(0);
        book.setWordNumber(0);
        Integer result = bookMapper.insertSelective(book);
        System.out.println(result);
        if(result == 0){
            apiResult = new APIResult("开书失败",false,500,null);
        }else {
            apiResult = new APIResult("开书成功，请等待审核",true,200,null);
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

    @Override
    public Pager getBookEntity(Integer writerId,String searchKey,String bookKind, Integer chapterTime, Integer clicks, Integer thumbs, Integer indexpage, Integer pagesize) {
        if(bookKind.equals("")||bookKind.equals("all")){
            bookKind = null;
        }
        if(indexpage == null){
            indexpage = 1;
        }
        if(pagesize == null){
            pagesize = 20;
        }
        String bookName = null;
        String writerName = null;
        if(!searchKey.equals("")){
            bookName = searchKey+"%";
            writerName = searchKey+"%";
        }
        List<BookEntity> list = bookEntityMapper.getBookEntitylist(writerId,writerName,bookName,bookKind,chapterTime,clicks,thumbs,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<BookEntity> books =  bookEntityMapper.getBookEntitylist(writerId,writerName,bookName,bookKind,chapterTime,clicks,thumbs,pager.getBeginrows(),pagesize);
        pager.setData(books);
        return pager;
    }
    @Override
    public Pager getBookKindList(Integer offset,Integer limit){
        Pager pager = new Pager(1,1);
        try{
            List<BookKind> list = bookKindMapper.getBookList(offset,limit);
            pager.setData(list);

        }catch (Exception e){
            e.printStackTrace();
        }

        return pager;
    }
}
