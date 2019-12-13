package com.magician.book.services;
//
//import com.magician.book.pojo.Writer;
//import com.magician.book.utils.APIRequest;
//import com.magician.book.utils.Pager;

import com.magician.book.pojo.Writer;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface WriterService {

    public APIResult getWriterInfoById(Integer writerId);


    public APIResult updateWriterInfo(Writer writer);

    //获取发给作者的消息
    public Pager getWriterMessage(Integer writerId);



}
