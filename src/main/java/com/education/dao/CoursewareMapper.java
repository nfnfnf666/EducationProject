package com.education.dao;

import com.education.pojo.Courseware;

import java.util.List;

public interface CoursewareMapper {

    List<Courseware> selectAllVideoCourseware(Integer courseListId);

    List<Courseware> selectAllDocumentCourseware(Integer courseListId);

    int deleteByPrimaryKey(Integer coursewareId);

    int insert(Courseware record);

    int insertSelective(Courseware record);

    Courseware selectByPrimaryKey(Integer coursewareId);

    int updateByPrimaryKeySelective(Courseware record);

    int updateByPrimaryKey(Courseware record);
}