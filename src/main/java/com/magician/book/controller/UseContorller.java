package com.magician.book.controller;

import com.magician.book.dao.BookEntityMapper;
import com.magician.book.pojo.*;
import com.magician.book.services.*;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.TokenUtil;
import com.magician.book.utils.UserType;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//游客的操作
@Controller
public class UseContorller {


    @Autowired
    BookService bookService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    WriterService writerService;

    @Autowired
    CommentServices commentServices;

    @Autowired
    UserService userService;

    @Autowired
    ThumbService thumbService;

    @Autowired
    RecommendService recommendService;

    @Autowired
    ReaderService readerService;

    @RequestMapping("/header")
    public String header(){
        return "reader/header";
    }
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Pager clicksbook = bookService.getBookEntity(null,"","",null,1,null,1,10);;
        Pager goodBook = bookService.getBookEntity(null,"","",null,null,1,1,15);
        Pager newChapter = bookService.getBookEntity(null,"","",1,null,null,1,15);
        Pager kind = bookService.getBookKindList(0,15);
        Pager recommend = recommendService.getReconmmendBook(1,7,1);

        modelAndView.setViewName("reader/index");
        modelAndView.addObject("goodBook",goodBook.getData());
        modelAndView.addObject("clicksbook",clicksbook.getData());
        modelAndView.addObject("newChapter",newChapter.getData());
        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("recommend",recommend.getData());
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }
    @RequestMapping("/reader/bookinfo/{bookId}")
    public ModelAndView bookinfo(@PathVariable Integer bookId, HttpServletRequest request, HttpSession session){
        //书籍访问量增加
        userService.addclicks(request,bookId);

        Integer readerId = (Integer) session.getAttribute("readerId");

        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        APIResult book = bookService.getBookById(bookId);
        Book bookinfo = (Book)book.getData();

        Pager chapter = chapterService.getChapterList(bookId,bookinfo.getWriterId(),"",1,"1",1,30);
        Pager clicksbook = bookService.getBookEntity(null,"","",null,1,null,1,10);;
        Pager writerBook = bookService.getWriterBooksById(bookinfo.getWriterId(),1,1,10);
        Pager commentPager = commentServices.getBookComment(bookId,1,8);
        boolean thumbType = thumbService.getThunmbType(bookId,readerId);


        modelAndView.setViewName("reader/bookinfo");
        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("clicksbook",clicksbook.getData());
        modelAndView.addObject("writerBook",writerBook.getData());
        modelAndView.addObject("bookinfo",bookinfo);
        modelAndView.addObject("chapters",chapter.getData());
        modelAndView.addObject("commentPager",commentPager);
        modelAndView.addObject("thumbType",thumbType);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

//    @RequestMapping("/reader/bookcomment/{bookId}")
//    public Pager bookinfo(@PathVariable Integer bookId,Integer indexpage,Integer pagesize){
//        Pager commentPager = commentServices.getBookComment(bookId,indexpage,pagesize);
//        return commentPager;
//    }

    @RequestMapping("/reader/mulu/{bookId}")
    public ModelAndView mulu(@PathVariable Integer bookId,Integer indexpage,Integer pagesize,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        APIResult book = bookService.getBookById(bookId);
        Book bookinfo = (Book)book.getData();
        Pager chapter = chapterService.getChapterList(bookId,bookinfo.getWriterId(),"",1,null,indexpage,pagesize);

        modelAndView.setViewName("reader/mulu");

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("bookinfo",bookinfo);
        modelAndView.addObject("chapters",chapter.getData());
        modelAndView.addObject("pager",chapter);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

    @RequestMapping("/reader/bookKind/{bookkindname}")
    public ModelAndView bookkind(@PathVariable String bookkindname,Integer indexpage,Integer pagesize,Integer clicks,Integer thumbs,HttpSession session){
        String type = null;
        if(clicks == null && thumbs == null){
            thumbs = 1;
        }
        if(clicks != null){
            type = "clicks";
        }else {
            type = "thumbs";
        }
        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        Pager books = bookService.getBookEntity(null,"",bookkindname,null,clicks,thumbs,indexpage,pagesize);
        Pager allkind = bookService.getBookKindList(0,25);
        modelAndView.setViewName("reader/bookclass");

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("books",books.getData());
        modelAndView.addObject("path",bookkindname);
        modelAndView.addObject("allkind",allkind.getData());
        modelAndView.addObject("type",type);
        modelAndView.addObject("bookkindname",bookkindname);
        modelAndView.addObject("pager",books);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

    @RequestMapping("/reader/writer/{writerId}")
    public ModelAndView bookkind(@PathVariable Integer writerId,Integer indexpage,Integer pagesize,HttpSession session){
        pagesize = 10;
        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        Pager allkind = bookService.getBookKindList(0,25);
        APIResult writer = writerService.getWriterInfoById(writerId);
        Pager books = bookService.getBookEntity(writerId,"","",1,null,null,indexpage,pagesize);
        modelAndView.setViewName("/reader/auorth");

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("allkind",allkind.getData());
        modelAndView.addObject("books",books.getData());
        modelAndView.addObject("writer",writer.getData());
        modelAndView.addObject("pager",books);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

    @RequestMapping("/reader/search")
    public ModelAndView search(String searchkey,Integer indexpage,Integer pagesize,HttpSession session){
        pagesize = 10;
        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        Pager books = bookService.getBookEntity(null,searchkey,"",null,1,null,indexpage,pagesize);
        modelAndView.setViewName("reader/search");

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("books",books.getData());
        modelAndView.addObject("searchKey",searchkey);
        modelAndView.addObject("pager",books);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

    @RequestMapping("/reader/chapter/{chapterId}")
    public ModelAndView read(@PathVariable Integer chapterId,HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        Pager kind = bookService.getBookKindList(0,15);
        Integer readerId = TokenUtil.getTokenUserId();
        APIResult r = chapterService.getChapterOneById(chapterId,null);
        Chapter c = null;
        if(r.getData() != null){
            c = (Chapter)r.getData() ;
        }

        APIResult bookResult = bookService.getBookById(c.getBookId());
        Book book = (Book) bookResult.getData();

        APIResult chapterResult = chapterService.ReaderByChapter(readerId,book,c);
        ChapterPreNextEntiy chapter = (ChapterPreNextEntiy) chapterResult.getData();

        modelAndView.setViewName("reader/read");

        modelAndView.addObject("bookKind",kind.getData());
        modelAndView.addObject("book",book);
        modelAndView.addObject("chapter",chapter);
        getReaderInfo(modelAndView,session);

        return modelAndView;
    }

    @RequestMapping("/reader/register")
    public ModelAndView register(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reader/register");


        return modelAndView;
    }
    @RequestMapping("/reader/login")
    public ModelAndView login(HttpSession session){
        session.removeAttribute("readerId");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reader/login");


        return modelAndView;
    }
    @RequestMapping("/reader/forget")
    public ModelAndView forget(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reader/forget");


        return modelAndView;
    }

    //判断登录状态
    @RequestMapping("/reader/loginstatus")
    @ResponseBody
    public APIResult loginstatus(HttpSession session){

        APIResult apiResult;
        Integer readerId = TokenUtil.getTokenUserId();
        if(readerId == null){
            apiResult = new APIResult("用户未登录",false,500,null);
        }else {
            apiResult = new APIResult("用户已登录",true,200,null);
        }


        return apiResult;
    }

    //获取用户的信息
    public void getReaderInfo(ModelAndView modelAndView,HttpSession session){

        Integer readerId = TokenUtil.getTokenUserId();
        if(TokenUtil.getTokenUserType()== null || TokenUtil.getTokenUserType()!= UserType.reader){
            readerId = null;
        }
        if(readerId == null){
            modelAndView.addObject("reader",null);
        }else {
            APIResult reader = readerService.getReaderById(readerId);
            modelAndView.addObject("reader",reader.getData());

        }
    }

    //加载评论
    @RequestMapping("/reader/getComment")
    @ResponseBody()
    public Pager getComment(Integer bookId,Integer indexpage,Integer pagesize){
        Pager commentPager = commentServices.getBookComment(bookId,indexpage,pagesize);
        return commentPager;
    }



}

