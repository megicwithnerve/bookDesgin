package com.magician.book.services.impls;

import com.magician.book.dao.BuyrecordMapper;
import com.magician.book.pojo.Buyrecord;
import com.magician.book.pojo.BuyrecordEntityAll;
import com.magician.book.pojo.BuyrecordEntiy;
import com.magician.book.pojo.Rechargerecord;
import com.magician.book.services.BuyRecordService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import com.magician.book.utils.ToEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BuyRecordServiceImpl implements BuyRecordService {

    @Resource
    BuyrecordMapper buyrecordMapper;

    @Override
    public APIResult insert(Buyrecord buyrecord) {
        buyrecord.setCreatedtime(new Date());

        APIResult apiResult = null;
        Integer result = buyrecordMapper.insertSelective(buyrecord);

        if(result == 0){
            apiResult = new APIResult("添加失败",false,500,null);
        }else {
            apiResult = new APIResult("添加成功",true,200,null);
        }

        return apiResult;
    }

    @Override
    public Pager getBuyRechargeList(Integer readerId, Integer indexpage, Integer pagesize) {

        List<Buyrecord> list = buyrecordMapper.getBuyRecord(readerId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Buyrecord> result = buyrecordMapper.getBuyRecord(readerId,pager.getBeginrows(),pagesize);
        pager.setData(result);
        return pager;
    }

    @Override
    public APIResult getBuyRechargeOne(Integer readerId, Integer bookId, Integer chapterId) {


        APIResult apiResult = null;
        Buyrecord result = buyrecordMapper.getBuyRecordByReaderIdChapterId(readerId,chapterId);

        if(result == null){
            apiResult = new APIResult("无数据",false,500,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,result);
        }

        return apiResult;
    }

    @Override
    public Pager getBuyRecordEntity(Integer readerId, Integer indexpage, Integer pagesize) {

        List<BuyrecordEntiy> list = buyrecordMapper.getBuyRecordEntity(readerId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<BuyrecordEntiy> result = buyrecordMapper.getBuyRecordEntity(readerId,pager.getBeginrows(),pagesize);
        pager.setData(result);
        return pager;
    }

    @Override
    public Pager getBuyRecordEntityByWriter(Integer writerId, Integer indexpage, Integer pagesize,String startTime,String endTime) {

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
        List<BuyrecordEntityAll> list = buyrecordMapper.getBuyRecordEntityByWriter(writerId,null,null,startTime,endTime);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<BuyrecordEntityAll> result = buyrecordMapper.getBuyRecordEntityByWriter(writerId,pager.getBeginrows(),pagesize,startTime,endTime);
        pager.setData(result);
        return pager;
    }

    @Override
    public Pager getBuyRecordEntityByBook(Integer bookId, Integer indexpage, Integer pagesize) {
        List<BuyrecordEntityAll> list = buyrecordMapper.getBuyRecordEntityByBook(bookId,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<BuyrecordEntityAll> result = buyrecordMapper.getBuyRecordEntityByBook(bookId,pager.getBeginrows(),pagesize);
        pager.setData(result);
        return pager;
    }
}
