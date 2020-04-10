package com.education.service;

import com.education.dao.CourselistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseListService {
    @Autowired
    CourselistMapper courselistMapper;

    public Integer selectCourseListIdByStudentIdAndCourseId(Integer studentId, Integer courseId){
        Map<String, Integer> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("courseId", courseId);
        return courselistMapper.selectCourseListIdByStudentIdAndCourseId(map);
    }

    public Integer selectCourseListIdByTeacherIdAndClassId(Integer teacherId, Integer classId){
        Map<String, Integer> map = new HashMap<>();
        map.put("teacherId", teacherId);
        map.put("classId", classId);
        return courselistMapper.selectCourseListIdByTeacherIdAndClassId(map);
    }

}
