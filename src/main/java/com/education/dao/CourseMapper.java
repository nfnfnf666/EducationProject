package com.education.dao;

import com.education.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    /**
     * 查询所有数据信息
     * @return 返回course集合
     */
    List<Course> selectCourse(@Param("courseName")String courseName);

    /**
     * 查询学生所修的所有课程信息
     * @param studentId
     * @return
     */
    List<Course> selectCourseByStudentId(Integer studentId);

    /**
     * 根据主键删除课程信息
     * @param courseId
     * @return
     */
    int deleteByPrimaryKey(Integer courseId);

    /**
     * 添加课程信息
     * @param record
     * @return
     */
    int insert(Course record);

    /**
     * 添加课程信息
     * @param record
     * @return
     */
    int insertSelective(Course record);

    /**
     * 根据主键ID查询课程信息
     * @param courseId
     * @return
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     * 修改课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 根据主键ID修改课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);
}