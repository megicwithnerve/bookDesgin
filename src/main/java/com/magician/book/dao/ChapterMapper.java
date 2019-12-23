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
    List<Chapter> getChapterList(Integer bookId,Integer chapterStatus,String order,Integer offset,Integer limit);

    //id获取
    Chapter getByChapterId(Integer chapterId,Integer chapterStatus);
}