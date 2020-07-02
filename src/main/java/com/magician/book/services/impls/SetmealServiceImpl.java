package com.magician.book.services.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.dao.SetmealMapper;
import com.magician.book.pojo.Setmeal;
import com.magician.book.services.SetmealService;
import com.magician.book.utils.APIRequest;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Resource
    SetmealMapper setmealMapper;

    @Override
    public APIResult insertPackage(Setmeal setmeal) {
        APIResult apiResult = null;
        setmeal.setIsDel(0);
        Integer result = setmealMapper.insertSelective(setmeal);
        if (result == 0){
            apiResult = new APIResult("插入失败",false,200,null);
        }else {
            apiResult = new APIResult("插入成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult updatePackage(Setmeal setmeal) {
        APIResult apiResult = null;
        Integer result = setmealMapper.updateByPrimaryKeySelective(setmeal);
        if (result == 0){
            apiResult = new APIResult("更新失败",false,200,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public Pager getPackageList(Integer indexpage, Integer pagesize,Integer isDel) {


        List<Setmeal> list = setmealMapper.getList(null,null,isDel);
        Pager pager = new Pager(list.size(),indexpage);
        List<Setmeal> result = setmealMapper.getList(pager.getBeginrows(),pagesize,isDel);
        pager.setData(result);

        return pager;
    }

    @Override
    public APIResult getPackageById(Setmeal setmeal) {
        APIResult apiResult = null;
        Setmeal result = setmealMapper.selectByPrimaryKey(setmeal.getSetmealId());
        if (result == null){
            apiResult = new APIResult("获取失败",false,200,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,result);
        }
        return apiResult;
    }
}
