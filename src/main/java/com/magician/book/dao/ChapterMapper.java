package com.magician.book.dao;

import com.magician.book.pojo.Chapter;

import java.util.List;

public interface ChapterMapper {
    int deleteByPrimaryKey(Integer chapterId);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer chapterId);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKeyWithBLOBs(Chapter record);

    int updateByPrimaryKey(Chapter record);

    //获取章节 索引 书 状态
    List<Chapter> getChapterList(Integer bookId,Integer writerId,String chapterName,Integer chapterStatus,String order,Integer offset,Integer limit);

    //id获取
    Chapter getByChapterId(Integer chapterId,Integer chapterStatus);

    //更具状态获取章节
//    List<Chapter> getChapterByStatus(Integer chapterStatus,Integer offset, Integer limit);

    //上一章与下一章
    Chapter getChapterIdPreNext(Integer chapterId ,Integer bookId,Integer pre,Integer next);
}