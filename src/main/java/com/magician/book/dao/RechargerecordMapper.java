package com.magician.book.dao;

import com.magician.book.pojo.Rechargerecord;

public interface RechargerecordMapper {
    int deleteByPrimaryKey(Integer rechargerecordId);

    int insert(Rechargerecord record);

    int insertSelective(Rechargerecord record);

    Rechargerecord selectByPrimaryKey(Integer rechargerecordId);

    int updateByPrimaryKeySelective(Rechargerecord record);

    int updateByPrimaryKey(Rechargerecord record);
}