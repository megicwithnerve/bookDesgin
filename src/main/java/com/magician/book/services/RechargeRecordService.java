package com.magician.book.services;

import com.magician.book.pojo.AlipayVo;
import com.magician.book.pojo.Rechargerecord;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface RechargeRecordService {

    //添加订单
    public APIResult insert(Rechargerecord rechargerecord);

    //更新订单
    public APIResult updateRecharge(Rechargerecord rechargerecord);

    //获取订单列表
    public Pager getRechargeList(Integer readerId, Integer indexpage, Integer pagesize);

    //根据id获取订单
    public APIResult getRechargeOne(Integer rechargerecordId);

    //根据支付宝订单 更新信息
    public APIResult updateRechargeByVo(AlipayVo alipayVo,String trade_no,Integer status);

    //管理员获取支付记录
    public Pager getRechargeListByAdmin(String readerName, Integer indexpage, Integer pagesize,String startTime,String endTime);

}
