package com.education.service;

import com.education.dao.CourseMapper;
import com.education.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询所有课程数据信息
     * @return 返回course集合
     */
    public List<Course> selectCourse(String courseName){
        return courseMapper.selectCourse(courseName);
    }

    /**
     * 查询学生所修的课程
     * @return
     */
    public List<Course> selectCourseByStudentId(Integer stduentId){
        return courseMapper.selectCourseByStudentId(stduentId);
    }

    /**
     * 根据主键删除数据
     * @param courseId  需要删除的数据的主键
     * @return 返回影响的条数
     */
    public int delCourse(Integer courseId){
        return courseMapper.deleteByPrimaryKey(courseId);
    }

    /**
     * 添加数据
     * @param record 需要添加的数据
     * @return 返回影响的条数
     */
    public int insert(Course record){
        return courseMapper.insertSelective(record);
    }

    /**
     * 根据主键查询
     * @param courseId 需要查询的数据的主键
     * @return 返回course对象
     */
    public Course selectByPrimaryKey(Integer courseId){
        return courseMapper.selectByPrimaryKey(courseId);
    }

    /**
     * 修改信息
     * @param course 修改后的信息封装的对象
     * @return
     */
    public int updateCourse(Course course){
        return courseMapper.updateByPrimaryKey(course);
    }

}
