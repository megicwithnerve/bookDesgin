package com.magician.book.services;

import com.magician.book.pojo.Recommend;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface RecommendService {
    //添加推荐
    public APIResult insertRecommend(Recommend recommend);

    //删除推荐
    public APIResult delRecommend(Recommend recommend);

    //更新推荐
    public APIResult updateRecommend(Recommend recommend);

    //获取推荐
    public APIResult getReconmmendById(Recommend recommend);

    public Pager getReconmmendList(Integer indexpage,Integer pagesize,Integer type);

    //推荐的书籍
    public Pager getReconmmendBook(Integer indexpage,Integer pagesize,Integer type);

}
