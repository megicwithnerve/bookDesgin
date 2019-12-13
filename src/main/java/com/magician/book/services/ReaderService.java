package com.magician.book.services;

//import com.magician.book.pojo.Reader;
//import com.magician.book.utils.APIRequest;

import com.magician.book.pojo.Reader;
import com.magician.book.utils.APIResult;

public interface ReaderService {

    //注册
    public APIResult ReaderResgiter(Reader reader);

    //修改个人信息
    public APIResult updateReader(Reader reader);

    //根据账号密码 查找读者
    public APIResult queryByPwd(String email,String pwd);

    //账号是否存在
    public APIResult queryByEmail(String email);

    //
}
