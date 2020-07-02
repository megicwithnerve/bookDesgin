package com.magician.book.services.impls;

import com.magician.book.dao.CollectionMapper;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.BookCollection;
import com.magician.book.pojo.BookCollectionEntity;
import com.magician.book.services.CollectionService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    CollectionMapper collectionMapper;


    @Override
    public APIResult insert(BookCollection bookCollection) {
        APIResult apiResult = null;
        BookCollection bookCollection1 = collectionMapper.getCollectionByReadIdBookId(bookCollection.getReaderId(),bookCollection.getBookId());

        if(bookCollection1 != null){
            apiResult = new APIResult("书籍已加入书架",false,500,null);

        }else {
            bookCollection.setUpdatetime(new Date());
            Integer result = collectionMapper.insertSelective(bookCollection);

            if(result == 0){
                apiResult = new APIResult("添加失败",false,500,null);
            }else {
                apiResult = new APIResult("添加成功",true,200,null);
            }

        }
        return apiResult;

    }

    @Override
    public APIResult delete(BookCollection bookCollection) {
        APIResult apiResult = null;

        Integer result = collectionMapper.deleteByPrimaryKey(bookCollection.getCollectionId());

        if(result == 0){
            apiResult = new APIResult("删除失败",false,500,null);
        }else {
            apiResult = new APIResult("删除成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public Pager getBookCollectionList(Integer readerId, Integer indexpage, Integer pagesize) {

        List<BookCollectionEntity> list = collectionMapper.getCollectionList(readerId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<BookCollectionEntity> bookCollectionEntities = collectionMapper.getCollectionList(readerId,pager.getBeginrows(),pagesize);
        pager.setData(bookCollectionEntities);
        return pager;
    }

    @Override
    public APIResult getBookCollectionOne(Integer readerId, Integer bookId) {

        APIResult apiResult = null;
        BookCollection bookCollection = collectionMapper.getCollectionByReadIdBookId(readerId,bookId);
        if(bookCollection == null){
            apiResult = new APIResult("获取失败",false,500,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,bookCollection);
        }
        return apiResult;
    }

    @Override
    public APIResult update(BookCollection bookCollection) {

        APIResult apiResult = null;
        bookCollection.setUpdatetime(new Date());
        Integer result = collectionMapper.updateByPrimaryKeySelective(bookCollection);

        if(result == 0){
            apiResult = new APIResult("更新失败",false,500,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,null);
        }

        return apiResult;
    }
}
