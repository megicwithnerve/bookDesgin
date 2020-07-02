package com.magician.book.services.impls;

import com.magician.book.dao.BookEntityMapper;
import com.magician.book.dao.BookMapper;
import com.magician.book.dao.RecommendMapper;
import com.magician.book.pojo.BookCollectionEntity;
import com.magician.book.pojo.BookEntity;
import com.magician.book.pojo.Recommend;
import com.magician.book.pojo.RecommendEntity;
import com.magician.book.services.RecommendService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    RecommendMapper recommendMapper;
    @Resource
    BookEntityMapper bookEntityMapper;

    @Override
    public APIResult insertRecommend(Recommend recommend) {
        APIResult apiResult = null;
        Integer result = recommendMapper.insertSelective(recommend);
        if (result == 0){
            apiResult = new APIResult("插入失败",false,200,null);
        }else {
            apiResult = new APIResult("插入成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult delRecommend(Recommend recommend) {
        APIResult apiResult = null;
        Integer result = recommendMapper.deleteByPrimaryKey(recommend.getRecommendId());
        if (result == 0){
            apiResult = new APIResult("删除失败",false,200,null);
        }else {
            apiResult = new APIResult("删除成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult updateRecommend(Recommend recommend) {
        APIResult apiResult = null;
        Integer result = recommendMapper.updateByPrimaryKeySelective(recommend);
        if (result == 0){
            apiResult = new APIResult("更新失败",false,200,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult getReconmmendById(Recommend recommend) {
        APIResult apiResult = null;
        RecommendEntity result = recommendMapper.getReconmmendById(recommend);
        if (result == null){
            apiResult = new APIResult("获取失败",false,200,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,result);
        }
        return apiResult;
    }

    @Override
    public Pager getReconmmendList(Integer indexpage, Integer pagesize, Integer type) {

        List<RecommendEntity> list = recommendMapper.getReconmmendList(null,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<RecommendEntity> Entities = recommendMapper.getReconmmendList(pager.getBeginrows(),pagesize,type);
        pager.setData(Entities);
        return pager;
    }

    @Override
    public Pager getReconmmendBook(Integer indexpage, Integer pagesize, Integer type) {

        List<RecommendEntity> list = recommendMapper.getReconmmendList(null,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<RecommendEntity> Entities = recommendMapper.getReconmmendList(pager.getBeginrows(),pagesize,type);
        List<BookEntity> l = new ArrayList<>();
       try {
           for (RecommendEntity r : Entities) {
               BookEntity bookEntity = bookEntityMapper.getBookEntityById(r.getBook().getBookId(),0,1);
               if(bookEntity != null && bookEntity.getBook().getBookStatus() == 1)
               l.add(bookEntity);
           }
       }catch (Exception e){
           e.printStackTrace();
       }

        pager.setData(l);
        return pager;
    }
}
