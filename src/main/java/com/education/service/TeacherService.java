package com.education.service;

import com.education.dao.TeacherMapper;
import com.education.pojo.Admin;
import com.education.pojo.Student;
import com.education.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    @Resource
    TeacherMapper teacherMapper;

    /**
     * 登录方法
     * @param teacher
     * @return
     */
    public List<Teacher> login(Teacher teacher) {
        return teacherMapper.login(teacher);
    }

    /**
     * 查询教师信息
     * @param teacherId  教师编号
     * @param teacherName 教师姓名
     * @return
     */
    public List<Teacher> queryTeacherList(String teacherId, String teacherName){
        Map<String,String> map=new HashMap();
        map.put("teacherId", teacherId);
        map.put("teacherName", teacherName);
        return teacherMapper.queryTeacherList(map);
    }

    /**
     * 根据教师ID删除教师
     * @param teacherId 教师ID
     * @return 返回修改的记录数
     */
    public int deleteTeacherById(int teacherId){
        return teacherMapper.deleteTeacherById(teacherId);
    }

    /**
     * 根据教师ID找教师
     * @param teacherId 教师ID
     * @return 教师对象
     */
    public Teacher queryTeacherById(int teacherId){
        return teacherMapper.queryTeacherById(teacherId);
    }

    /**
     * 添加教师
     * @param teacher 教师信息
     * @return 返回修改的记录数
     */
    public int insertTeacher(Teacher teacher){
        System.out.println("teacherService");
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改教师信息
     * @param teacher 教师信息
     * @return
     */
    public int update(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }

}
