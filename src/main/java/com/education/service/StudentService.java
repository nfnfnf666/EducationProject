package com.education.service;

import com.education.dao.StudentMapper;
import com.education.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Resource
    StudentMapper studentMapper;

    /**
     * 登录方法
     * @param student
     * @return
     */
    public List<Student> login(Student student) {
        return studentMapper.login(student);
    }



    /**
     * 查询学生信息
     * @param studentId  学生编号
     * @param studentName 学生姓名
     * @return
     */
    public List<Student> queryStudentList(String studentId, String studentName){
        Map<String,String> map=new HashMap();
        map.put("studentId", studentId);
        map.put("studentName", studentName);
        return studentMapper.queryStudentList(map);
    }

    /**
     * 根据学生ID删除学生
     * @param studentId 学生ID
     * @return 返回修改的记录数
     */
    public int deleteStudentById(int studentId){
        return studentMapper.deleteStudentById(studentId);
    }

    /**
     * 根据学生ID找学生
     * @param studentId 学生ID
     * @return 学生对象
     */
    public Student queryStudentById(int studentId){
        return studentMapper.queryStudentById(studentId);
    }

    /**
     * 添加学生
     * @param student 学生信息
     * @return 返回修改的记录数
     */
    public int insertStudent(Student student){
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生信息
     * @param student 学生信息
     * @return
     */
    public int update(Student student){
        return studentMapper.updateStudent(student);
    }

}
