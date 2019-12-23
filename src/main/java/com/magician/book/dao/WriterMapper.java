package com.magician.book.dao;

import com.magician.book.pojo.Writer;

import java.util.List;

public interface WriterMapper {
    int deleteByPrimaryKey(Integer writerId);

    int insert(Writer record);

    int insertSelective(Writer record);

    Writer selectByPrimaryKey(Integer writerId);

    int updateByPrimaryKeySelective(Writer record);

    int updateByPrimaryKey(Writer record);

    Writer getByEmailPwd(String email,String password);

    List<Writer> getByemail(String email);

    //获取条数
    Integer WriterCount(String searchName,Integer status);
    //分页获取
    List<Writer> searchWriter(String searchName,Integer status,Integer offset,Integer limit);
}