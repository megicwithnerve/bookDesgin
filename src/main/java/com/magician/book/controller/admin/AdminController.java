package com.magician.book.controller.admin;

import com.github.pagehelper.PageInfo;
import com.magician.book.pojo.*;
import com.magician.book.services.*;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    WriterService writerService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;
    @Autowired
    SetmealService setmealService;
    @Autowired
    RecommendService recommendService;
    @Autowired
    RechargeRecordService rechargeRecordService;
    @Autowired
    BuyRecordService buyRecordService;
    @Autowired
    CommentServices commentServices;

    //用户列表
    @RequestMapping("/admin/serachReader")
    @ResponseBody
    public Pager serachReader(@RequestParam("indexpage") Integer indexpage,
                              @RequestParam("pagesize") Integer pagesize,
                              @RequestParam("searchName") String searchName,
                              @RequestParam("status") Integer status){
        return readerService.searchReader(searchName,status,indexpage,pagesize);
    }

    @RequestMapping("/admin/readerBuyRecord")
    @ResponseBody
    public Pager editReader(@RequestParam("readerId") Integer readerId,
                                @RequestParam("indexpage") Integer indexpage,
                                @RequestParam("pagesize") Integer pagesize){
        return buyRecordService.getBuyRecordEntity(readerId,indexpage,pagesize);
    }

    @RequestMapping("/admin/editReader")
    @ResponseBody
    public APIResult editReader(Reader reader){
        return readerService.updateReader(reader);
    }

    //管理员获取用户的购买记录
    @RequestMapping("/admin/readerBuyInfo")
    @ResponseBody
    public Pager readerBuyInfo(Reader reader,Integer indexpage,Integer pagesize){
        return buyRecordService.getBuyRecordEntity(reader.getReaderId(),indexpage,pagesize);
    }

    @RequestMapping("/admin/searchWriter")
    @ResponseBody
    public Pager getWriters(@RequestParam("indexpage") Integer indexpage,
                            @RequestParam("pagesize") Integer pagesize,
                            @RequestParam("searchName") String searchName,
                            @RequestParam("status") Integer status){
        return writerService.searchWriter(indexpage,pagesize,searchName,status);
    }

    @RequestMapping("/admin/editWriter")
    @ResponseBody
    public APIResult editWriter(Writer writer){
        return writerService.updateWriterInfo(writer);
    }

    //支付列表
    @RequestMapping("/admin/searchPayinfo")
    @ResponseBody
    public Pager searchPayinfo(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                               @RequestParam("searchName") String searchName,
                               @RequestParam("startTime") String startTime,
                               @RequestParam("endTime") String endTime){
        return rechargeRecordService.getRechargeListByAdmin(searchName,indexpage,pagesize,startTime,endTime);
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

    @RequestMapping("/admin/getWriterById")
    @ResponseBody
    public APIResult getWriterById(Integer writerId){
        return writerService.getWriterInfoById(writerId);
    }

//    @RequestMapping("/admin/editMessage")
//    @ResponseBody
//    public APIResult editMessage(Message message){
//        return new APIResult();
//    }

    @RequestMapping("/admin/addSetmeal")
    @ResponseBody
    public APIResult addSetmal(Setmeal setmeal){
        return setmealService.insertPackage(setmeal);
    }

    @RequestMapping("/admin/searchSetmeal")
    @ResponseBody
    public Pager searchSetmeal(@RequestParam("indexpage") Integer indexpage,
                                   @RequestParam("pagesize") Integer pagesize){
        return setmealService.getPackageList(indexpage,pagesize,null);
    }

    //获取单个
    @RequestMapping("/admin/getSetmealById")
    @ResponseBody
    public APIResult delSetmeal(Setmeal setmeal){
        return setmealService.getPackageById(setmeal);
    }

    @RequestMapping("/admin/editSetmeal")
    @ResponseBody
    public APIResult editSetmeal(Setmeal setmeal){
        return setmealService.updatePackage(setmeal);
    }

    //首页推荐待定 ？？ 关联表 带推荐等级 1——5
    @RequestMapping("/admin/addRecommend")
    @ResponseBody
    public APIResult addRecommend(Recommend recommend){
        return recommendService.insertRecommend(recommend);
    }

    @RequestMapping("/admin/searchRecommend")
    @ResponseBody
    public Pager searchRecommend(@RequestParam("indexpage") Integer indexpage,
                               @RequestParam("pagesize") Integer pagesize,
                                 @RequestParam("type" ) Integer type){
        return recommendService.getReconmmendList(indexpage,pagesize,type);
    }

    //获取单个
    @RequestMapping("/admin/getRecommendById")
    @ResponseBody
    public APIResult getRecommendById(Recommend recommend){
        return recommendService.getReconmmendById(recommend);
    }

    @RequestMapping("/admin/editRecommend")
    @ResponseBody
    public APIResult editRecommend(Recommend recommend){
        return recommendService.updateRecommend(recommend);
    }
    //删除
    @RequestMapping("/admin/delRecommend")
    @ResponseBody
    public APIResult delRecommend(Recommend recommend){
        return recommendService.delRecommend(recommend);
    }


    /**
     * 书籍列表
     * @param
     * @return
     */
    @RequestMapping("/admin/booklist")
    @ResponseBody
    public Pager booklist(@RequestParam("indexpage") Integer indexpage,
                          @RequestParam("pagesize") Integer pagesize,
                          @RequestParam("searchValue") String searchValue,
                          @RequestParam("bookStatus") Integer bookStatus,
                          @RequestParam("writerId") Integer writerId) {
        return bookService.searchBooks("",writerId,searchValue,"",bookStatus,null,null,indexpage,pagesize);
    }

    /**
     * 拿一本书
     * @param bookId
     * @return
     */
    @RequestMapping("/admin/bookById")
    @ResponseBody
    public APIResult booklist(@RequestParam("bookId") Integer bookId) {
        return bookService.getBookById(bookId);
    }

    //搜索书籍
    @RequestMapping("/admin/search")
    @ResponseBody
    public Pager booklist(@RequestParam("searchValue") String searchValue) {
        return bookService.getBookEntity(null,searchValue,"",null,null,null,1,25);
    }

    /**
     * 修改book
     * @param book
     * @return
     */
    @RequestMapping("/admin/editbook")
    @ResponseBody
    public APIResult editbook(Book book) {


        return bookService.updateBook(book);
    }
    @RequestMapping("/admin/bookKind")
    @ResponseBody
    public Pager getBookKind() {

        return bookService.getBookKindList(0,25);
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
    @RequestMapping("/admin/getchapter")
    @ResponseBody
    public Pager getchapter(@RequestParam("indexpage") Integer indexpage,
                            @RequestParam("pagesize") Integer pagesize,
                            @RequestParam("bookId") Integer bookId,
                            @RequestParam("chapterName") String chapterName,
                            @RequestParam("chapterStatus") Integer chapterStatus,
                            @RequestParam("writerId") Integer writerId){
        return chapterService.getChapterList(bookId,writerId,chapterName,chapterStatus,null,indexpage,pagesize);
    }

    /**
     * 根据id获取章节
     * @param chapterId
     * @return
     */
    @RequestMapping("/admin/getChapterById")
    @ResponseBody
    public APIResult getChapterById(@RequestParam("chapterId") Integer chapterId){
//        System.out.println(chapterId);
        return chapterService.getChapterOneById(chapterId,null);
    }
    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @RequestMapping("/admin/editchapter")
    @ResponseBody
    public APIResult editchapter(Chapter chapter){

        return chapterService.UpdateChapter(chapter);
    }
    //书籍的评论
    @RequestMapping("/admin/bookRecommend")
    @ResponseBody
    public Pager getBookRecommend(Integer bookId,Integer indexpage,Integer pagesize){

        return commentServices.getBookComment(bookId,indexpage,pagesize);
    }
    //删除评论
    @RequestMapping("/admin/delComment")
    @ResponseBody
    public APIResult delComment(Comment comment){

        return commentServices.delBookComment(null,comment.getCommentId(),null,null);
    }
}
