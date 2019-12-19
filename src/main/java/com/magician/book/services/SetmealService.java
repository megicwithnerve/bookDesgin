package com.magician.book.services;

import com.github.pagehelper.PageInfo;
import com.magician.book.pojo.Setmeal;
import com.magician.book.utils.APIRequest;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface SetmealService {
    //增加套餐
    public APIResult insertPackage(Setmeal setmeal);
    //修改套餐
    public APIResult updatePackage(Setmeal setmeal);
    //获取套餐
    public PageInfo getPackageList(Integer indexpage, Integer pagesze);
}
