package com.magician.book.services;
//
//import com.magician.book.pojo.Writer;
//import com.magician.book.utils.APIRequest;
//import com.magician.book.utils.Pager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.pojo.Writer;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

import javax.servlet.http.HttpSession;

public interface WriterService {

    public APIResult getWriterInfoById(Integer writerId);


    public APIResult updateWriterInfo(Writer writer);

    //登录
    public APIResult getWriterBypwd(String email, String pwd, HttpSession session);

    //注册
    public APIResult insertWriter(Writer writer);

    //查找邮箱唯一
    public APIResult onlyOneWriter(String email);

    //搜索作者
    public Pager searchWriter(Integer indexpage,Integer pagesize,String writerName,Integer status);

    //获取发给作者的消息
    public Pager getWriterMessage(Integer writerId);

    public APIResult updatePwd(String oldpwd,String newpwd,String repwd,Integer writerId);

}
