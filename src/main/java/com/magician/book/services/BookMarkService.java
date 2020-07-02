package com.magician.book.services;

import com.magician.book.pojo.Bookmark;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface BookMarkService {

    //增加书签
    APIResult insert(Integer bookId,Integer chapterId,Integer readerId);
    //移除书签
    APIResult delete(Bookmark bookmark);
    //获取书签
    Pager getBookMarkList(Integer collectionId,Integer indexpage,Integer pagesize);
    //获取单个
    APIResult getBookMarkOne(Bookmark bookmark);

}
