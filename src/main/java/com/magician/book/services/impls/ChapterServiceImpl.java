package com.magician.book.services.impls;

import com.magician.book.dao.*;
import com.magician.book.pojo.*;
import com.magician.book.services.BookService;
import com.magician.book.services.ChapterService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.StringHtml;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    ChapterMapper chapterMapper;
    @Resource
    BookMapper bookMapper;
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    ReaderMapper readerMapper;
    @Resource
    BuyrecordMapper buyrecordMapper;
    @Resource
    WriterMapper writerMapper;

    @Override
    public Pager getChapterBystatus(Integer chapterStatus, Integer indexpage, Integer pagesize) {

        List<Chapter> list = chapterMapper.getChapterList(null,null,null,chapterStatus,null,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Chapter> chapters = chapterMapper.getChapterList(null,null,null,chapterStatus,null,pager.getBeginrows(),pagesize);
        pager.setData(chapters);
        return pager;
    }

    @Override
    public APIResult AddChapter(Chapter chapter) {
        APIResult apiResult = null;

        chapter.setCreatedtime(new Date());
        chapter.setChapterStatus(0);

        Integer price = StringHtml.stripHtml(chapter.getContent()).length()/3 * chapter.getPrice() /1000;
        chapter.setPrice(price);

        Integer result = chapterMapper.insertSelective(chapter);
        if(result == 0){
            apiResult = new APIResult("添加失败",false,500,null);

        }else {
             Book book = bookMapper.selectByPrimaryKey(chapter.getBookId());
             Integer number = book.getWordNumber() + StringHtml.stripHtml(chapter.getContent()).length()/3;
             book.setWordNumber(number);
             bookMapper.updateByPrimaryKeySelective(book);
            apiResult = new APIResult("添加成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult UpdateChapter(Chapter chapter) {
        chapter.setUpdatetime(new Date());
        APIResult apiResult ;

        Chapter chapter1 = chapterMapper.getByChapterId(chapter.getChapterId(),null);

        Book booktest = bookMapper.selectByPrimaryKey(chapter1.getBookId());
        if(chapter.getContent() != null && !chapter.getContent().equals("")){
            Integer price = StringHtml.stripHtml(chapter.getContent()).length()/3;
            Integer bookprice = booktest.getPrice();
            chapter.setPrice(price * bookprice /1000 );
        }

        Integer result = chapterMapper.updateByPrimaryKeySelective(chapter);

        if(result == 0) {
            apiResult = new APIResult("更新失败", false, 500, null);
        }else {
            if(chapter.getContent() != null ){
                Book book = bookMapper.selectByPrimaryKey(chapter1.getBookId());
                Integer number = book.getWordNumber() + StringHtml.stripHtml(chapter.getContent()).length()
                        - StringHtml.stripHtml(chapter1.getContent()).length();
//                System.out.println(number);
                book.setWordNumber(number);
                bookMapper.updateByPrimaryKeySelective(book);
            }

            apiResult = new APIResult("更新成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public Pager getChapterList(Integer bookId,Integer writerId,String chapterName, Integer chapterStatus, String order, Integer indexpage, Integer pagesize) {
        if(!chapterName.equals("")){
            chapterName = chapterName+"%";
        }else {
            chapterName = null;
        }
        if (indexpage == null){
            indexpage = 1;
        }
        if (pagesize == null){
            pagesize = 300;
        }
        List<Chapter> list = chapterMapper.getChapterList(bookId,writerId,chapterName,chapterStatus,order,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Chapter> chapters = chapterMapper.getChapterList(bookId,writerId,chapterName,chapterStatus,order,pager.getBeginrows(),pagesize);
        pager.setData(chapters);
        return pager;
    }

    @Override
    public APIResult getChapterOneById(Integer chapterId,Integer chapterStatus) {
        APIResult apiResult;

        Chapter result = chapterMapper.getByChapterId(chapterId,chapterStatus);
        if(result == null){
            apiResult = new APIResult("无法获取数据",false,500,null);
        }else {
            apiResult = new APIResult("获取数据",true,200,result);
        }

        return apiResult;
    }


    @Override
    public APIResult ReaderByChapter(Integer readerId, Book book, Chapter chapter) {


        APIResult apiResult;


        //判断是否有
        if(chapter == null){
            return new APIResult("无法获取",false,500,null);
        }
        //判断是否收费 收费 足够就购买 不足则提示不够
        if(chapter.getVipChapter() == 1 && chapter.getChapterStatus() == 1){
            if(readerId != null){
                Reader reader = readerMapper.selectByPrimaryKey(readerId);

                Buyrecord b1 = buyrecordMapper.getBuyRecordByReaderIdChapterId(readerId,chapter.getChapterId());
                if(reader.getBalance() > chapter.getPrice() && b1 == null){//余额足够且没买过


                    reader.setBalance(reader.getBalance()-chapter.getPrice());
                    readerMapper.updateByPrimaryKeySelective(reader);
                    Buyrecord buyrecord = new Buyrecord();
                    buyrecord.setBookId(book.getBookId());
                    buyrecord.setCreatedtime(new Date());
                    buyrecord.setPrice(chapter.getPrice());
                    buyrecord.setReaderId(readerId);
                    buyrecord.setWriterId(book.getWriterId());
                    buyrecord.setChapterId(chapter.getChapterId());
                    buyrecordMapper.insertSelective(buyrecord);
                    Writer writer = writerMapper.selectByPrimaryKey(book.getWriterId());
                    Integer p = writer.getProfit()+chapter.getPrice();
                    writer.setProfit(p);
                    writerMapper.updateByPrimaryKeySelective(writer);

                    BookCollection bookCollection = collectionMapper.getCollectionByReadIdBookId(readerId,book.getBookId());
                    if(bookCollection == null){
                        bookCollection = new BookCollection();
                        bookCollection.setReaderId(readerId);
                        bookCollection.setBookId(book.getBookId());
                        bookCollection.setChapterId(chapter.getChapterId());
                        bookCollection.setUpdatetime(new Date());
                        collectionMapper.insertSelective(bookCollection);
                    }

                }else if(reader.getBalance() < chapter.getPrice()){
                    chapter.setContent("余额不足，请充值！！！");
                }

            }else {
                chapter.setContent("VIP章节请登录后，购买阅读");
            }


        }else {
            if(chapter.getChapterStatus() == 0)
            chapter.setContent("章节内容已经下架");
        }
        //同步阅读记录 没有加入书架则不同步
        BookCollection bookCollection = collectionMapper.getCollectionByReadIdBookId(readerId,book.getBookId());
        if(bookCollection != null){
            bookCollection.setChapterId(chapter.getChapterId());
            bookCollection.setUpdatetime(new Date());
            collectionMapper.updateByPrimaryKeySelective(bookCollection);

        }

        //上一章 下一章
        Chapter pre = chapterMapper.getChapterIdPreNext(chapter.getChapterId(),book.getBookId(),1,null);
        Chapter next = chapterMapper.getChapterIdPreNext(chapter.getChapterId(),book.getBookId(),null,1);

        ChapterPreNextEntiy chapterPreNextEntiy = new ChapterPreNextEntiy();
        chapterPreNextEntiy.setChapter(chapter);
        chapterPreNextEntiy.setPre(pre);
        chapterPreNextEntiy.setNext(next);
        apiResult = new APIResult("获取成功",true,200,chapterPreNextEntiy);

        return apiResult;
    }

    @Override
    public APIResult getChapterPreNext(Integer chapter, Integer pre, Integer next) {
        return null;
    }
}
