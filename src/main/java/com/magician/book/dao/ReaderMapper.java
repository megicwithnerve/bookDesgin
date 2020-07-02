package com.magician.book.dao;

import com.magician.book.pojo.Reader;
import com.magician.book.pojo.Writer;

import java.util.List;

public interface ReaderMapper {
    int deleteByPrimaryKey(Integer redaerId);

    int insert(Reader record);

    int insertSelective(Reader record);

    Reader selectByPrimaryKey(Integer readerId);

    int updateByPrimaryKeySelective(Reader record);

    int updateByPrimaryKey(Reader record);

    Reader getByEmailPwd(String email, String password);

    List<Reader> getByemail(String email);

    //获取条数
    Integer ReaderCount(String searchName,Integer status);
    //分页获取
    List<Reader> searchReader(String searchName,Integer status,Integer offset,Integer limit);
}