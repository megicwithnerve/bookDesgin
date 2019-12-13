package com.magician.book.dao;

import com.magician.book.pojo.Thumb;

public interface ThumbMapper {
    int deleteByPrimaryKey(Integer thumbId);

    int insert(Thumb record);

    int insertSelective(Thumb record);

    Thumb selectByPrimaryKey(Integer thumbId);

    int updateByPrimaryKeySelective(Thumb record);

    int updateByPrimaryKey(Thumb record);
}