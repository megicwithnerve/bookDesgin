package com.magician.book.dao;

import com.magician.book.pojo.Buyrecord;
import com.magician.book.pojo.BuyrecordEntityAll;
import com.magician.book.pojo.BuyrecordEntiy;
import com.magician.book.pojo.Rechargerecord;
import io.swagger.models.auth.In;

import java.util.List;

public interface BuyrecordMapper {
    int deleteByPrimaryKey(Integer buyrecordId);

    int insert(Buyrecord record);

    int insertSelective(Buyrecord record);

    Buyrecord selectByPrimaryKey(Integer buyrecordId);

    int updateByPrimaryKeySelective(Buyrecord record);

    int updateByPrimaryKey(Buyrecord record);

    //购买记录列表
    List<Buyrecord> getBuyRecord(Integer readerId, Integer offset, Integer limit);

    //获取一条购买记录
    Buyrecord getBuyRecordByReaderIdChapterId(Integer readerId, Integer chapterId);

    //购买实体列表
    List<BuyrecordEntiy> getBuyRecordEntity(Integer readerId, Integer offset, Integer limit);

    //根据作者
    List<BuyrecordEntityAll> getBuyRecordEntityByWriter(Integer writerId, Integer offset, Integer limit,String startTime,String endTime);

    //根据书籍
    List<BuyrecordEntityAll> getBuyRecordEntityByBook(Integer bookId, Integer offset, Integer limit);
}