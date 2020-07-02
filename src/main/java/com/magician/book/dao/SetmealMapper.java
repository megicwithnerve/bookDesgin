package com.magician.book.dao;

import com.magician.book.pojo.Setmeal;

import java.util.List;

public interface SetmealMapper {
    int deleteByPrimaryKey(Integer setmealId);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    Setmeal selectByPrimaryKey(Integer setmealId);

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);

    List<Setmeal> getList(Integer offset, Integer limit,Integer isDel);
}