package com.education.service;

import com.education.dao.TaskMapper;
import com.education.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    public List<Task> selectAllTask(Integer courselistId){
        return taskMapper.selectAllTask(courselistId);
    }

    public Task queryTaskById(Integer taskId){
        return taskMapper.selectByPrimaryKey(taskId);
    }

}
