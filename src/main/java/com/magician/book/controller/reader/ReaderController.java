package com.magician.book.controller.reader;

import com.magician.book.dao.BookmarkMapper;
import com.magician.book.pojo.*;
import com.magician.book.services.*;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller()
@RequestMapping("reader/")//login
public class ReaderController {

    @Autowired
    BookService bookService;

    @Autowired
    ReaderService readerService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    BookMarkService bookMarkService;

    @Autowired
    CommentServices commentServices;

    @Autowired
    BuyRecordService buyRecordService;

    @Autowired
    RechargeRecordService rechargeRecordService;

    @Autowired
    SetmealService setmealService;

    @Autowired
    ThumbService thumbService;

    @RequestMapping("/getComments")
    @ResponseBody
    public Pager getCommnets(Integer bookId, Integer indexpage){

        return new Pager(indexpage,10);
    }

    @RequestMapping("/myinfo")
    public ModelAndView myinfo(HttpSession session){
       // session.setAttribute("readerId",1);

        Integer readerId = TokenUtil.getTokenUserId();
        ModelAndView modelAndView = new ModelAndView();
        APIResult reader = readerService.getReaderById(readerId);
        Pager kind = bookService.getBookKindList(0,15);
        modelAndView.addObject("bookKind",kind.getData());

        modelAndView.setViewName("reader/myinfo");
        modelAndView.addObject("reader",reader.getData());
        return modelAndView;
    }

    @RequestMapping("/editpassword")
    public ModelAndView editpassword(HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();

        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        APIResult reader = readerService.getReaderById(readerId);

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("reader",reader.getData());
        modelAndView.setViewName("reader/editpassword");
        return modelAndView;
    }

    @RequestMapping("/updateinfo")
    @ResponseBody
    public APIResult updateinf(Reader reader,HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        reader.setReaderId(readerId);
        APIResult result = readerService.updateReader(reader);
        return result;
    }

    @RequestMapping("/updatepwd")
    @ResponseBody
    public APIResult updatepwd(String oldpwd,String newpwd,String repwd,HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        return readerService.updatePwd(oldpwd,newpwd,repwd,readerId);
    }
    //购买记录
    @RequestMapping("/buyrecord")
    public ModelAndView buyrecord(Integer indexpage,Integer pagesize,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Integer readerId = TokenUtil.getTokenUserId();
        APIResult reader = readerService.getReaderById(readerId);
        Pager kind = bookService.getBookKindList(0,15);
        Pager buyrecord = buyRecordService.getBuyRecordEntity(readerId,indexpage == null ? 1:indexpage,pagesize == null ? 8:pagesize);
        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("buyrecord",buyrecord);
        modelAndView.addObject("reader",reader.getData());


        modelAndView.setViewName("reader/buyrecord");
        return modelAndView;
    }

    //套餐
    @RequestMapping("/order")
    public ModelAndView order(HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();

        ModelAndView modelAndView = new ModelAndView();
        APIResult reader = readerService.getReaderById(readerId);
        Pager kind = bookService.getBookKindList(0,15);
        Pager order = setmealService.getPackageList(1,6,1);

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("setmeal",order.getData());
//        System.out.println(order.getData().size());
        modelAndView.addObject("reader",reader.getData());


        modelAndView.setViewName("reader/order");
        return modelAndView;
    }

    //支付成功页面
    @RequestMapping("/ok")
    public ModelAndView successHtml(HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();

        ModelAndView modelAndView = new ModelAndView();
        APIResult reader = readerService.getReaderById(readerId);
        Pager kind = bookService.getBookKindList(0,15);
//        Pager order = setmealService.getPackageList(1,6,1);

        modelAndView.addObject("bookKind",kind.getData());
//        modelAndView.addObject("setmeal",order.getData());
//        System.out.println(order.getData().size());
        modelAndView.addObject("reader",reader.getData());


        modelAndView.setViewName("reader/ok");
        return modelAndView;
    }

    //充值记录
    @RequestMapping("/orderrecord")
    public ModelAndView orderrecord(Integer indexpage,Integer pagesize,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Integer readerId = TokenUtil.getTokenUserId();
        APIResult reader = readerService.getReaderById(readerId);
        Pager kind = bookService.getBookKindList(0,15);
        Pager record = rechargeRecordService.getRechargeList(readerId,indexpage == null ? 1:indexpage,pagesize == null ? 8:pagesize);

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("record",record);
        modelAndView.addObject("reader",reader.getData());


        modelAndView.setViewName("reader/orderrecord");
        return modelAndView;
    }

    //我的书架
    @RequestMapping("/mybook")
    public ModelAndView mybook(Integer indexpage,Integer pagesize,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Integer readerId = TokenUtil.getTokenUserId();
        Pager list = collectionService.getBookCollectionList(readerId,indexpage,pagesize);
        modelAndView.addObject("collections",list);
        modelAndView.setViewName("reader/mybook");
        return modelAndView;
    }
    //添加书籍
    @RequestMapping("/addmybook")
    @ResponseBody()
    public APIResult addmybook(BookCollection bookCollection, HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        System.out.println(readerId);
        bookCollection.setReaderId(readerId);
        return collectionService.insert(bookCollection);
    }
    //获取书签
    @RequestMapping("/mybookmark")
    @ResponseBody
    public Pager mybookmark(Integer indexpage,Integer pagesize,Integer collectionId){
        ModelAndView modelAndView = new ModelAndView();
        Pager list = bookMarkService.getBookMarkList(collectionId,indexpage,pagesize);
        return list;
    }
    //添加书签
    @RequestMapping("/addmybookmark")
    @ResponseBody()
    public APIResult addmybookmark(Integer bookId,Integer chapterId, HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        return bookMarkService.insert(bookId,chapterId,readerId);
    }
    //移除书籍
    @RequestMapping("/delmybook")
    @ResponseBody()
    public APIResult delmybook(BookCollection bookCollection, HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        bookCollection.setReaderId(readerId);
        return collectionService.delete(bookCollection);
    }
    //移除书签
    @RequestMapping("/delbookmark")
    @ResponseBody()
    public APIResult delbookmark(Bookmark bookmark, HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        return bookMarkService.delete(bookmark);
    }

    //添加评论
    @RequestMapping("/addComment")
    @ResponseBody()
    public APIResult addComment(Comment comment, HttpSession session){
        Integer readerId = TokenUtil.getTokenUserId();
        comment.setReaderId(readerId);
        return commentServices.insertBookComment(comment);
    }

        //点赞数改变
    @RequestMapping(value = "/thumbBook")
    @ResponseBody
    public APIResult increaseLikeCount(Thumb thumb,Integer status, HttpSession session){

        Integer readerId = TokenUtil.getTokenUserId();
        thumb.setReaderId(readerId);
        return thumbService.getThumbOne(thumb,status);
    }




}
