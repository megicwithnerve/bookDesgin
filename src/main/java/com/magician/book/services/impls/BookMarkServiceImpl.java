package com.magician.book.services.impls;

import com.magician.book.dao.BookmarkMapper;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.BookCollection;
import com.magician.book.pojo.BookMarkEntiy;
import com.magician.book.pojo.Bookmark;
import com.magician.book.services.BookMarkService;
import com.magician.book.services.CollectionService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {

    @Resource
    BookmarkMapper bookmarkMapper;

    @Autowired
    CollectionService collectionService;

    @Override
    public APIResult insert(Integer bookId,Integer chapterId,Integer readerId) {
        APIResult apiResult = null;
        Bookmark bookmark = new Bookmark();
        APIResult cresult = collectionService.getBookCollectionOne(readerId,bookId);
        if(!cresult.isResult()){
            BookCollection bookCollection = new BookCollection();
            bookCollection.setReaderId(readerId);
            bookCollection.setBookId(bookId);
            bookCollection.setChapterId(chapterId);
            collectionService.insert(bookCollection);
            cresult = collectionService.getBookCollectionOne(readerId,bookId);
        }
        BookCollection bookCollection = (BookCollection) cresult.getData();
        bookmark.setChapterId(chapterId);
        bookmark.setCollectionId(bookCollection.getCollectionId());

        Bookmark bookmarkresult = bookmarkMapper.getBookMarkOne(bookmark);

        if(bookmarkresult == null){
            Integer result = bookmarkMapper.insertSelective(bookmark);

            if(result == 0){
                apiResult = new APIResult("添加失败",false,500,null);
            }else {
                apiResult = new APIResult("添加成功",true,200,null);
            }
        }else {
            apiResult = new APIResult("已添加书签",false,500,null);
        }


        return apiResult;
    }

    @Override
    public APIResult delete(Bookmark bookmark) {
        APIResult apiResult = null;
        Integer result = bookmarkMapper.deleteByPrimaryKey(bookmark.getBookmarkId());

        if(result == 0){
            apiResult = new APIResult("删除失败",false,500,null);
        }else {
            apiResult = new APIResult("删除成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public Pager getBookMarkList(Integer collectionId,Integer indexpage,Integer pagesize) {

        List<BookMarkEntiy> list = bookmarkMapper.searchBookmark(collectionId,null,null);
        Pager pager = new Pager(list.size(),indexpage);
        List<BookMarkEntiy> bookmarks = bookmarkMapper.searchBookmark(collectionId,pager.getBeginrows(),pagesize);
        pager.setData(bookmarks);
        return pager;
    }

    @Override
    public APIResult getBookMarkOne(Bookmark bookmark) {

        APIResult apiResult = null;
        Bookmark result = bookmarkMapper.getBookMarkOne(bookmark);

        if(result == null){
            apiResult = new APIResult("获取失败",false,500,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,result);
        }
        return apiResult;
    }
}
