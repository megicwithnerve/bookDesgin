package com.magician.book.services.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.dao.SetmealMapper;
import com.magician.book.pojo.Setmeal;
import com.magician.book.services.SetmealService;
import com.magician.book.utils.APIRequest;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealMapper setmealMapper;

    @Override
    public APIResult insertPackage(Setmeal setmeal) {
        APIResult apiResult = null;
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
    public PageInfo getPackageList(Integer indexpage, Integer pagesze) {

        PageHelper.startPage(indexpage,pagesze);
        List<Setmeal> list = setmealMapper.getList();
        PageInfo pageInfo = new PageInfo(list,5);

        return pageInfo;
    }
}
