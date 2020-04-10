package com.education.dao;

import com.education.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    List<Student> login(Student student);

    /**
     * 查询学生信息
     *@param map  存放查询的条件
     * @return
     */
    List<Student> queryStudentList(Map map);

    /**
     * 根据学生ID删除学生
     * @param studentId 学生ID
     * @return 返回修改的记录数
     */
    int deleteStudentById(Integer studentId);

    /**
     * 根据学生ID查询学生
     * @param studentId
     * @return
     */
    Student queryStudentById(Integer studentId);

    /**
     * 添加学生
     * @param student 学生信息
     * @return 返回修改的记录数
     */
    int insertStudent(Student student);

    /**
     * 修改学生信息
     * @param student 学生信息
     * @return
     */
    int updateStudent(Student student);



    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}