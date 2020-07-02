package com.magician.book.services;

import com.magician.book.pojo.Buyrecord;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface BuyRecordService {

    //添加购买记录
    public APIResult insert(Buyrecord buyrecord);
    //获取购买记录
    public Pager getBuyRechargeList(Integer readerId,Integer indexpage, Integer pagesize);
    //获取单个购买记录
    public APIResult getBuyRechargeOne(Integer readerId,Integer bookId,Integer chapterId);
    //获取购买记录实体
    public Pager getBuyRecordEntity(Integer readerId,Integer indexpage, Integer pagesize);
    //作者获取自己书籍的购买记录 待定
    public Pager getBuyRecordEntityByWriter(Integer writerId,Integer indexpage, Integer pagesize,String startTime,String endTime);
    //获取书籍的购买记录
    public Pager getBuyRecordEntityByBook(Integer bookId,Integer indexpage, Integer pagesize);

}
