package com.magician.book.dao;

import com.magician.book.pojo.Reader;

public interface ReaderMapper {
    int deleteByPrimaryKey(Integer redaerId);

    int insert(Reader record);

    int insertSelective(Reader record);

    Reader selectByPrimaryKey(Integer redaerId);

    int updateByPrimaryKeySelective(Reader record);

    int updateByPrimaryKey(Reader record);
}