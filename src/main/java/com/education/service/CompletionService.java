package com.education.service;

import com.education.dao.CompletionMapper;
import com.education.pojo.Completion;
import com.education.pojo.CompletionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompletionService {
    @Resource
    CompletionMapper completionMapper;

    public int insertCompletion(Completion completion){
        return completionMapper.insert(completion);
    }

    public Completion selectCompletionByTaskIdAndStduentId(Integer taskId, Integer studentId){
        Map<String,Integer> map = new HashMap();
        map.put("taskId", taskId);
        map.put("studentId", studentId);
        return completionMapper.selectCompletionByTaskIdAndStduentId(map);
    }

    public List<CompletionVo> queryCompletionVoList(Integer taskId){
        return completionMapper.queryCompletionVoList(taskId);
    }

    public int updateScoreById(String completionId, String completionScore){
        Map<String, String> map = new HashMap<>();
        map.put("completionId", completionId);
        map.put("completionScore", completionScore);
        return completionMapper.updateScoreById(map);
    }

}
