package com.magician.book.controller.admin;

import com.magician.book.pojo.Reader;
import com.magician.book.pojo.Setmeal;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @RequestMapping("/admin/serachReader")
    @ResponseBody
    public Pager serachReader(@RequestParam("indexpage") Integer indexpage,
                              @RequestParam("pagesize") Integer pagesize,
                              @RequestParam("searchValue") String searchValue,
                              @RequestParam("readerStatus") String readerStatus){
        return new Pager(0,1);
    }
    @RequestMapping("/admin/editReader")
    @ResponseBody
    public APIResult editReader(Reader reader){
        return new APIResult();
    }

    @RequestMapping("/admin/serachWriter")
    @ResponseBody
    public Pager serachWriter(@RequestParam("indexpage") Integer indexpage,
                              @RequestParam("pagesize") Integer pagesize,
                              @RequestParam("searchValue") String searchValue,
                              @RequestParam("writerStatus") String readerStatus){
        return new Pager(0,1);
    }

    @RequestMapping("/admin/serachChapter")
    @ResponseBody
    public Pager serachChapter(@RequestParam("indexpage") Integer indexpage,
                              @RequestParam("pagesize") Integer pagesize,
                              @RequestParam("searchValue") String searchValue,
                              @RequestParam("chapterStatus") String chapterStatus){
        return new Pager(0,1);
    }

    @RequestMapping("/admin/editWriter")
    @ResponseBody
    public APIResult editWriter(Reader reader){
        return new APIResult();
    }

    @RequestMapping("/admin/searchPayinfo")
    @ResponseBody
    public Pager searchPayinfo(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                               @RequestParam("searchValue") String searchValue,
                               @RequestParam("startTime") String startTime,
                               @RequestParam("endTime") String endTime){
        return new Pager(0,1);
    }

    @RequestMapping("/admin/getMessage")
    @ResponseBody
    public Pager searchMessage(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                               @RequestParam("searchValue") String searchValue,
                               @RequestParam("startTime") String startTime,
                               @RequestParam("endTime") String endTime){
        return new Pager(0,1);
    }

    @RequestMapping("/admin/editMessage")
    @ResponseBody
    public APIResult editMessage(Message message){
        return new APIResult();
    }

    @RequestMapping("/admin/addSetmeal")
    @ResponseBody
    public APIResult addSetmal(Setmeal setmeal){
        return new APIResult();
    }

    @RequestMapping("/admin/searchSetmeal")
    @ResponseBody
    public Pager searchSetmeal(@RequestParam("indexpage") Integer indexpage,
                                   @RequestParam("pagesize") Integer pagesize,
                                   @RequestParam("searchValue") String searchValue){
        return new Pager(0,1);
    }

    @RequestMapping("/admin/delSetmeal")
    @ResponseBody
    public APIResult delSetmeal(Setmeal setmeal){
        return new APIResult();
    }


    //首页推荐待定


}
