package com.education.dao;

import com.education.pojo.Student;
import com.education.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {

    List<Teacher> login(Teacher teacher);
    
    /**
     *验证手机号码
     * @return 返回查询到的记录数，json字符串
     */
    int checkPhone(@Param("phone") String phone);

    /**
     * 查询教师信息
     *@param map  存放查询的条件
     * @return
     */
    List<Teacher> queryTeacherList(Map map);

    /**
     * 根据教师ID删除教师
     * @param teacherId 教师ID
     * @return 返回修改的记录数
     */
    int deleteTeacherById(Integer teacherId);

    /**
     * 根据教师ID查询教师
     * @param teacherId
     * @return
     */
    Teacher queryTeacherById(Integer teacherId);

    /**
     * 添加教师
     * @param teacher 教师信息
     * @return 返回修改的记录数
     */
    int insertTeacher(Teacher teacher);

    /**
     * 修改教师信息
     * @param teacher 教师信息
     * @return
     */
    int updateTeacher(Teacher teacher);







    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}