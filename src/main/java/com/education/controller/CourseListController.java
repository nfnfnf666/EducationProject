package com.education.controller;

import com.education.pojo.Student;
import com.education.pojo.Teacher;
import com.education.service.CourseListService;
import com.education.service.StudentService;
import com.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CourseListController {
    @Autowired
    CourseListService courseListService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping("courseHome")
    public ModelAndView courseHome(@RequestParam("studentId") Integer studentId,
                                   @RequestParam("courseId") Integer courseId,
                                   HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        ModelAndView mav = null;
        Integer courselistId = courseListService.selectCourseListIdByStudentIdAndCourseId(studentId, courseId);
        httpSession.setAttribute("courselistId", courselistId);
        Student student = studentService.queryStudentById(studentId);
        System.out.println("courseHome.do中的courselistId值为："+courselistId);
        mav = new ModelAndView("/student/courseHomePage");
        mav.addObject("courselistId", courselistId);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping("teacherCourseHome")
    public ModelAndView teacherCourseHome(@RequestParam("teacherId") Integer teacherId,
                                   @RequestParam("classId") Integer classId,
                                   HttpServletRequest request){
        ModelAndView mav = null;
        Integer courselistId = courseListService.selectCourseListIdByTeacherIdAndClassId(teacherId, classId);
        request.getSession().setAttribute("courselistId", courselistId);
        Teacher teacher = teacherService.queryTeacherById(teacherId);
        System.out.println("teacherCourseHome.do中的courselistId值为："+courselistId);
        mav = new ModelAndView("/teacher/courseHomePage");
        mav.addObject("courselistId", courselistId);
        mav.addObject("teacher", teacher);
        return mav;
    }
}
