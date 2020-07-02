package com.magician.book.dao;

import com.magician.book.pojo.Recommend;
import com.magician.book.pojo.RecommendEntity;

import java.util.List;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer recommendId);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer recommendId);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

    RecommendEntity getReconmmendById(Recommend recommend);

    List<RecommendEntity> getReconmmendList(Integer offset,Integer limit,Integer type);
}