package com.education.service;

import com.education.dao.CompletionMapper;
import com.education.pojo.Completion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompletionService {
    @Resource
    CompletionMapper completionMapper;

    public int insertCompletion(Completion completion){
        return completionMapper.insert(completion);
    }

    public Completion selectCompletionByTaskIdAndStduentId(Integer taskId, Integer studentId){
        Map<String,Integer> map=new HashMap();
        map.put("taskId", taskId);
        map.put("studentId", studentId);
        return completionMapper.selectCompletionByTaskIdAndStduentId(map);
    }

}
