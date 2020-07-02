package com.magician.book.dao;

import com.magician.book.pojo.RechargeRecordEntity;
import com.magician.book.pojo.Rechargerecord;

import java.util.List;

public interface RechargerecordMapper {
    int deleteByPrimaryKey(Integer rechargerecordId);

    int insert(Rechargerecord record);

    int insertSelective(Rechargerecord record);

    Rechargerecord selectByPrimaryKey(Integer rechargerecordId);

    int updateByPrimaryKeySelective(Rechargerecord record);

    int updateByPrimaryKey(Rechargerecord record);

    //充值记录列表
    List<Rechargerecord> getRechargerecord(Integer readerId, Integer offset, Integer limit);

    //更具订单id 获取记录
    Rechargerecord selectByGradeNo(String outTradeNo);

    //admin获取订单
    List<RechargeRecordEntity> getRechargerecordByAdmin(String readerName,Integer offset,Integer limit,String startTime,String endTime);


}