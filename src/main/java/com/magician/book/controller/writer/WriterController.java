package com.magician.book.controller.writer;

import com.alipay.api.domain.PageInfo;
import com.magician.book.pojo.*;
import com.magician.book.services.*;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.TokenUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    WriterService writerService;

    @Autowired
    BookService bookService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    RecommendService recommendService;

    @Autowired
    CommentServices commentServices;

    @Autowired
    BuyRecordService buyRecordService;


    @RequestMapping("/writer/getinfo")
    @ResponseBody
    public APIResult writerinfo(){
        Integer writerId = TokenUtil.getTokenUserId();
        return writerService.getWriterInfoById(writerId);
    }


    @RequestMapping("/writer/editinfo")
    @ResponseBody
    public APIResult writereditinfo(Writer writer){
        Integer writerId = TokenUtil.getTokenUserId();
        writer.setWriterId(writerId);
        return writerService.updateWriterInfo(writer);
    }
    @RequestMapping("/writer/updatePwd")
    @ResponseBody
    public APIResult updatePwd(String oldpwd,String newpwd,String repwd){
        Integer writerId = TokenUtil.getTokenUserId();
        System.out.println(writerId);
        return writerService.updatePwd(oldpwd,newpwd, repwd,writerId);
    }

    @RequestMapping("/writer/bookKind")
    @ResponseBody
    public Pager getBookKind() {

        return bookService.getBookKindList(0,25);
    }

    @RequestMapping("/writer/newbook")
    @ResponseBody
    public APIResult newbook(Book book) {
        Integer writerId = TokenUtil.getTokenUserId();
        APIResult writer = writerService.getWriterInfoById(writerId);
        Writer w = (Writer) writer.getData();
        book.setWriterName(w.getWriterName());
        book.setWriterId(writerId);
        return bookService.insertBook(book);
    }

    /**
     * 作者的所有书
     * @param
     * @return
     */
    @RequestMapping("/writer/booklist")
    @ResponseBody
    public Pager booklist(@RequestParam("indexpage") Integer indexpage,
                           @RequestParam("pagesize") Integer pagesize,
//                           @RequestParam("searchvalue") String searchcvalue,
                          @RequestParam("bookStatus") Integer bookStatus) {
        Integer writerId = TokenUtil.getTokenUserId();
        return bookService.getWriterBooksById(writerId,bookStatus,indexpage,pagesize);
    }

    /**
     * 作者拿一本书
     * @param bookId
     * @return
     */
    @RequestMapping("/writer/bookById")
    @ResponseBody
    public APIResult booklist(@RequestParam("bookId") Integer bookId) {
        return bookService.getBookById(bookId);
    }

    /**
     * 修改book
     * @param book
     * @return
     */
    @RequestMapping("/writer/editbook")
    @ResponseBody
    public APIResult editbook(Book book) {


        return bookService.updateBook(book);
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

    /**
     * 作者增加章节
     * @param chapter
     * @return
     */
    @RequestMapping("/writer/addchapter")
    @ResponseBody
    public APIResult addchapter(Chapter chapter){
        Integer writerId = TokenUtil.getTokenUserId();
        chapter.setWriterId(writerId);
        return chapterService.AddChapter(chapter);
    }

    /**
     * 获取章节
     * @param indexpage
     * @param pagesize
     * @param bookId
     * @param chapterName
     * @param chapterStatus
     * @return
     */
    @RequestMapping("/writer/getchapter")
    @ResponseBody
    public Pager getchapter(@RequestParam("indexpage") Integer indexpage,
                            @RequestParam("pagesize") Integer pagesize,
                            @RequestParam("bookId") Integer bookId,
                            @RequestParam("chapterName") String chapterName,
                            @RequestParam("chapterStatus") Integer chapterStatus){
        Integer writerId = TokenUtil.getTokenUserId();
        return chapterService.getChapterList(bookId,writerId,chapterName,chapterStatus,null,indexpage,pagesize);
    }

    /**
     * 根据id获取章节
     * @param chapterId
     * @return
     */
    @RequestMapping("/writer/getChapterById")
    @ResponseBody
    public APIResult getChapterById(@RequestParam("chapterId") Integer chapterId){

        return chapterService.getChapterOneById(chapterId,null);
    }
    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @RequestMapping("/writer/editchapter")
    @ResponseBody
    public APIResult editchapter(Chapter chapter){
        return chapterService.UpdateChapter(chapter);
    }

    @RequestMapping("/writer/searchwriter")
    @ResponseBody
    public Pager getWriters(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                               @RequestParam("searchName") String searchName,
                               @RequestParam("status") Integer status){
        return null;
    }


    //作者查看自己的书籍的购买记录
    @RequestMapping("/writer/bookBuyRecord")
    @ResponseBody
    public Pager bookBuyRecord(Integer indexpage,Integer pagesize,String startTime,String endTime){
        Integer writerId = TokenUtil.getTokenUserId();
        return buyRecordService.getBuyRecordEntityByWriter(writerId,indexpage,pagesize,startTime,endTime);
    }
    //书籍的评论
    @RequestMapping("/writer/bookRecommend")
    @ResponseBody
    public Pager getBookRecommend(Integer bookId,Integer indexpage,Integer pagesize){

        return commentServices.getBookComment(bookId,indexpage,pagesize);
    }
    //删除评论
    @RequestMapping("/writer/delComment")
    @ResponseBody
    public APIResult delComment(Comment comment){

        return commentServices.delBookComment(null,comment.getCommentId(),null,null);
    }
}
