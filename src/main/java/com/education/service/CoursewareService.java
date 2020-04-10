package com.education.service;

import com.education.dao.CoursewareMapper;
import com.education.pojo.Courseware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursewareService {
    @Autowired
    CoursewareMapper coursewareMapper;

    public List<Courseware> selectAllVideoCourseware(Integer courseListId){
        return coursewareMapper.selectAllVideoCourseware(courseListId);
    }

    public List<Courseware> selectAllDocumentCourseware(Integer courseListId){
        return coursewareMapper.selectAllDocumentCourseware(courseListId);
    }

    public Courseware selectCoursewareById(Integer coursewareId){
        return coursewareMapper.selectByPrimaryKey(coursewareId);
    }

    public int deleteCoursewareById(Integer coursewareId){
        return coursewareMapper.deleteByPrimaryKey(coursewareId);
    }

    public int insertCourseware(Courseware courseware){
        return coursewareMapper.insert(courseware);
    }

}
