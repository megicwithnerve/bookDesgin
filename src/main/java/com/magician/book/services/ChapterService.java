package com.magician.book.services;

//import com.magician.book.pojo.Chapter;
//import com.magician.book.utils.APIRequest;
//import com.magician.book.utils.Pager;

import com.magician.book.pojo.Book;
import com.magician.book.pojo.Chapter;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.Pager;

public interface ChapterService {
    //待审核章节
    public Pager getChapterBystatus(Integer chapterStatus,Integer indexpage, Integer pagesize);

    //增加章节
    public APIResult AddChapter(Chapter chapter);

    //更新章节
    public APIResult UpdateChapter(Chapter chapter);

    //章节列表
    public Pager getChapterList(Integer bookId,Integer writerId,String chapterName,Integer chapterStatus,String order,Integer indexpage, Integer pagesize);

    //id获取章节
    public APIResult getChapterOneById(Integer chapterId,Integer chapterStatus);

    //购买章节
    public APIResult ReaderByChapter(Integer readerId, Book book, Chapter chapter);

    //相邻章节
    public APIResult getChapterPreNext(Integer chapter,Integer pre,Integer next);
}
