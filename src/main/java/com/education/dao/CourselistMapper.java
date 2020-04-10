package com.education.dao;

import com.education.pojo.Courselist;

import java.util.Map;

public interface CourselistMapper {

    Integer selectCourseListIdByStudentIdAndCourseId(Map map);

    Integer selectCourseListIdByTeacherIdAndClassId(Map map);

    int deleteByPrimaryKey(Integer courselistId);

    int insert(Courselist record);

    int insertSelective(Courselist record);

    Courselist selectByPrimaryKey(Integer courselistId);

    int updateByPrimaryKeySelective(Courselist record);

    int updateByPrimaryKey(Courselist record);
}