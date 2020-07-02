package com.magician.book.services;

import com.magician.book.pojo.Book;
import com.magician.book.pojo.BookCollection;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;
import io.swagger.models.auth.In;

public interface CollectionService {

    //增加收藏
    APIResult insert(BookCollection bookCollection);
    //移除收藏
    APIResult delete(BookCollection bookCollection);
    //获取收藏
    Pager getBookCollectionList(Integer readerId, Integer indexpage, Integer pagesize);

    APIResult getBookCollectionOne(Integer readerId, Integer bookId);
    //更新收藏

    APIResult update(BookCollection bookCollection);
}
