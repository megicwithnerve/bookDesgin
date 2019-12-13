package com.magician.book.services;

//import com.magician.book.pojo.Chapter;
//import com.magician.book.utils.APIRequest;
//import com.magician.book.utils.Pager;

import com.magician.book.pojo.Chapter;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface ChapterService {
    //增加章节
    public APIResult AddChapter(Chapter chapter);

    //更新章节
    public APIResult UpdateChapter(Chapter chapter);

    //
    public Pager getChapterList(Integer indexpage, Integer pagesize,Integer bookId);

    //id获取章节
    public APIResult getChapterOneById(Integer chapterId);
}
