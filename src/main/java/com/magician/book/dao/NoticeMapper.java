package com.magician.book.dao;

import com.magician.book.pojo.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);
}