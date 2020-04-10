package com.education.dao;

import com.education.pojo.Completion;

import java.util.Map;

public interface CompletionMapper {
    int deleteByPrimaryKey(Integer completionId);

    Completion selectCompletionByTaskIdAndStduentId(Map map);

    int insert(Completion record);

    int insertSelective(Completion record);

    Completion selectByPrimaryKey(Integer completionId);

    int updateByPrimaryKeySelective(Completion record);

    int updateByPrimaryKeyWithBLOBs(Completion record);

    int updateByPrimaryKey(Completion record);
}