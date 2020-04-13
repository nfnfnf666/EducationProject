package com.education.dao;

import com.education.pojo.Completion;
import com.education.pojo.CompletionVo;

import java.util.List;
import java.util.Map;

public interface CompletionMapper {
    int deleteByPrimaryKey(Integer completionId);

    Completion selectCompletionByTaskIdAndStduentId(Map map);

    List<CompletionVo> queryCompletionVoList(Integer taskId);

    int insert(Completion record);

    int insertSelective(Completion record);

    Completion selectByPrimaryKey(Integer completionId);

    int updateByPrimaryKeySelective(Completion record);

    int updateByPrimaryKeyWithBLOBs(Completion record);

    int updateByPrimaryKey(Completion record);
}