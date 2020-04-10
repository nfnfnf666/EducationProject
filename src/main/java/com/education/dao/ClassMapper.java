package com.education.dao;

import com.education.pojo.Class;
import com.education.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {

    /**
     * 查询所有数据信息
     * @return 返回class集合
     */
    List<Class> selectClass(@Param("className")String classname);

    List<Class> selectClassByTeacherId(Integer teacherId);

    int deleteByPrimaryKey(Integer classId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}