package com.magician.book.services.impls;

import com.magician.book.dao.ReaderMapper;
import com.magician.book.dao.RechargerecordMapper;
import com.magician.book.pojo.*;
import com.magician.book.services.RechargeRecordService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.RechargeEnum;
import com.magician.book.utils.ToEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Resource
    RechargerecordMapper rechargerecordMapper;
    @Resource
    ReaderMapper readerMapper;

    @Override
    public APIResult insert(Rechargerecord rechargerecord) {
        APIResult apiResult = null;
        Integer result = rechargerecordMapper.insertSelective(rechargerecord);

        if(result == 0){
            apiResult = new APIResult("添加失败",false,500,null);
        }else {
            apiResult = new APIResult("添加成功",true,200,rechargerecord);
        }

        return apiResult;
    }

    @Override
    public APIResult updateRecharge(Rechargerecord rechargerecord) {
        APIResult apiResult = null;

        Integer result = rechargerecordMapper.updateByPrimaryKeySelective(rechargerecord);

        if(result == 0){
            apiResult = new APIResult("更新失败",false,500,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,rechargerecord);
        }

        return apiResult;
    }

    @Override
    public Pager getRechargeList(Integer readerId, Integer indexpage, Integer pagesize) {

        List<Rechargerecord> list = rechargerecordMapper.getRechargerecord(readerId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Rechargerecord> result = rechargerecordMapper.getRechargerecord(readerId,pager.getBeginrows(),pagesize);
        pager.setData(result);

        return pager;
    }

    @Override
    public APIResult getRechargeOne(Integer rechargerecordId) {
        APIResult apiResult = null;
        Rechargerecord result = rechargerecordMapper.selectByPrimaryKey(rechargerecordId);

        if(result == null){
            apiResult = new APIResult("获取失败",false,500,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,result);
        }

        return apiResult;
    }

    @Override
    public APIResult updateRechargeByVo(AlipayVo alipayVo,String trade_no,Integer status) {
        APIResult apiResult = null;
        Rechargerecord r1 = rechargerecordMapper.selectByGradeNo(alipayVo.getOut_trade_no());
        //根据检查状态状态更新数据 0 消息队列、失败检查 1支付成功检查
        if(status == 0){
            //关闭未支付的订单
            if(r1.getStatus() == 0){
                r1.setStatus(2);
                r1.setTradeNo(trade_no);
                rechargerecordMapper.updateByPrimaryKeySelective(r1);
            }
        }else if(status == 1){
            //更新订单数据
            r1.setStatus(1);
            r1.setTradeNo(trade_no);
            rechargerecordMapper.updateByPrimaryKeySelective(r1);
            //更新用户数据
            Reader reader = readerMapper.selectByPrimaryKey(r1.getReaderId());
            Integer count = reader.getBalance()+r1.getTicketNumber()+r1.getDiscount();
            reader.setBalance(count);
            readerMapper.updateByPrimaryKeySelective(reader);

        }
        return apiResult;
    }

    @Override
    public Pager getRechargeListByAdmin(String readerName, Integer indexpage, Integer pagesize, String startTime, String endTime) {

        if(readerName == null){
            readerName = "";
        }
        readerName+= "%";
        if(startTime.equals("")){
            startTime = null;
        }

        if(endTime.equals("")){
            endTime = null;
        }else{
            try {

                endTime += " 23:59:59";

            }catch (Exception e){
                e.printStackTrace();
            }


        }
        List<RechargeRecordEntity> list = rechargerecordMapper.getRechargerecordByAdmin(readerName,null,null,startTime,endTime);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<RechargeRecordEntity> result = rechargerecordMapper.getRechargerecordByAdmin(readerName,pager.getBeginrows(),pagesize,startTime,endTime);
        for (RechargeRecordEntity r:result) {
            r.setStatusEnum(RechargeEnum.getRechargeEnum(r.getStatus()));
        }
        pager.setData(result);

        return pager;
    }
}
