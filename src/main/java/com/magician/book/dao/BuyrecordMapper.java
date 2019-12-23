package com.magician.book.dao;

import com.magician.book.pojo.Buyrecord;
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

}