package com.magician.book.controller.writer;

import com.alipay.api.domain.PageInfo;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.Chapter;
import com.magician.book.pojo.Writer;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WriterController {




    @RequestMapping("/writer/getinfo")
    @ResponseBody
    public APIResult writerinfo(){
        return new APIResult();
    }

    @RequestMapping("/writer/editinfo")
    @ResponseBody
    public APIResult writereditinfo(Writer writer){
        return new APIResult();
    }


    @RequestMapping("/writer/newbook")
    @ResponseBody
    public APIResult newbook(Book book) {

        return new APIResult();
    }

    /**
     * 作者的所有书
     * @param searchtype 书籍的状 searchvalue 书籍的名字
     * @return
     */
    @RequestMapping("/writer/booklist")
    @ResponseBody
    public Pager booklist(@RequestParam("indexpage") Integer indexpage,
                           @RequestParam("pagesize") Integer pagesize,
                           @RequestParam("searchvalue") String searchcvalue,
                          @RequestParam("searchtype") String searchtype) {
        return new Pager(0,indexpage);
    }




    @RequestMapping("/writer/editbook")
    @ResponseBody
    public APIResult editbook(Book book) {

        APIResult apiResult = new APIResult();
        return apiResult;
    }

    @RequestMapping("/writer/message")
    @ResponseBody
    public Pager getmessage(){
        return new Pager(0,1);
    }

    @RequestMapping("/writer/editmessage")
    @ResponseBody
    public APIResult editmessage(){
        return new APIResult();
    }

    @RequestMapping("/writer/addchapter")
    @ResponseBody
    public APIResult addchapter(){
        return new APIResult();
    }
    @RequestMapping("/writer/getchapter")
    @ResponseBody
    public Pager getchapter(@RequestParam("indexpage") Integer indexpage,
                            @RequestParam("pagesize") Integer pagesize,
                            @RequestParam("bookId") Integer bookId,
                            @RequestParam("chapterstatus") String chapterstatus){
        return new Pager(0,1);
    }

    @RequestMapping("/writer/editchapter")
    @ResponseBody
    public APIResult editchapter(Chapter chapter){
        return new APIResult();
    }

    @RequestMapping("/writer/searchwriter")
    @ResponseBody
    public Pager getWriters(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                               @RequestParam("searchName") String searchName,
                               @RequestParam("status") Integer status){
        return null;
    }
}
