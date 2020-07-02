package com.magician.book.services;

import com.magician.book.pojo.Thumb;
import com.magician.book.utils.APIResult;

public interface ThumbService {

    //点赞 取消点赞  1/0
    APIResult getThumbOne(Thumb thumb, Integer status);
    //获取点赞状态
    boolean getThunmbType(Integer bookId,Integer readerId);

}
