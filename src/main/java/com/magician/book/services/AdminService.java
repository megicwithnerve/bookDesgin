package com.magician.book.services;

import com.magician.book.utils.APIRequest;
import com.magician.book.utils.APIResult;

public interface AdminService {

    //登录
    public APIResult queryByPwd(String email, String pwd);

    //
}
