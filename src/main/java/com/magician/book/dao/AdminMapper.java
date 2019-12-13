package com.magician.book.dao;

import com.magician.book.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminMapper {
    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectByEmailAndPwd(String loginName,String loginPassword);
}