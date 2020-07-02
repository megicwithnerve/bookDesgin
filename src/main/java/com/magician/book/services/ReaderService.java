package com.magician.book.services;

//import com.magician.book.pojo.Reader;
//import com.magician.book.utils.APIRequest;

import com.magician.book.pojo.Reader;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

import javax.servlet.http.HttpSession;

public interface ReaderService {

    //注册
    public APIResult ReaderResgiter(Reader reader,String code);

    //修改个人信息
    public APIResult updateReader(Reader reader);

    //根据账号密码 查找读者
    public APIResult queryByPwd(String email,String pwd, HttpSession session);

    //账号是否存在
    public APIResult queryByEmail(String email);

    //搜索读者
    public Pager searchReader(String searchName,Integer status,Integer indexpage,Integer pagesize);

    //根据id获取读者
    public APIResult getReaderById(Integer readerId);

    //根据原密码更新密码
    public APIResult updatePwd(String oldpwd,String newpwd,String repwd,Integer readerId);
}
