package com.magician.book.dao;

import com.magician.book.pojo.Writer;

public interface WriterMapper {
    int deleteByPrimaryKey(Integer writerId);

    int insert(Writer record);

    int insertSelective(Writer record);

    Writer selectByPrimaryKey(Integer writerId);

    int updateByPrimaryKeySelective(Writer record);

    int updateByPrimaryKey(Writer record);
}