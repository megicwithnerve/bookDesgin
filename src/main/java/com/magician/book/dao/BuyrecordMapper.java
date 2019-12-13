package com.magician.book.dao;

import com.magician.book.pojo.Buyrecord;

public interface BuyrecordMapper {
    int deleteByPrimaryKey(Integer buyrecordId);

    int insert(Buyrecord record);

    int insertSelective(Buyrecord record);

    Buyrecord selectByPrimaryKey(Integer buyrecordId);

    int updateByPrimaryKeySelective(Buyrecord record);

    int updateByPrimaryKey(Buyrecord record);
}