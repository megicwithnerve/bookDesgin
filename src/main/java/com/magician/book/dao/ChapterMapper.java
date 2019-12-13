package com.magician.book.dao;

import com.magician.book.pojo.Chapter;

public interface ChapterMapper {
    int deleteByPrimaryKey(Integer chapterId);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer chapterId);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKeyWithBLOBs(Chapter record);

    int updateByPrimaryKey(Chapter record);
}