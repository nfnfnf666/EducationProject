package com.education.controller;

import com.education.pojo.Class;
import com.education.pojo.*;
import com.education.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;

    @RequestMapping("/login.do")
    public ModelAndView login(@RequestParam("userId") Integer userId, @RequestParam("password") String pwd,
                              @RequestParam("userType") String userType,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              RedirectAttributes attr) throws IOException {
        System.out.println("进入UserController中的login方法");
        System.out.println("userId"+userId+"pwd"+pwd+"userType"+userType);
        ModelAndView mav=null;
        if("student".equals(userType)){
            Student student = new Student();
            student.setStudentId(userId);
            student.setStudentPwd(pwd);
            List<Student> studentList = studentService.login(student);
            System.out.println("studentList长度是：" + studentList.size());
            if(studentList.size() > 0){
                List<Course> courseList = courseService.selectCourseByStudentId(userId);
                request.getSession().setAttribute("studentId", userId);
                System.out.println("login.do中存入session的studentId的值为："+(Integer)request.getSession().getAttribute("studentId"));
                mav = new ModelAndView("/student/courseList");
                mav.addObject("courseList", courseList);
                mav.addObject("studentList", studentList);
            } else {
                attr.addAttribute("message","账号名或密码错误");
                mav = new ModelAndView("redirect:/user/login.jsp");
            }
        } else if("teacher".equals(userType)){
            Teacher teacher = new Teacher();
            teacher.setTeacherId(userId);
            teacher.setTeacherPwd(pwd);
            List<Teacher> teacherList = teacherService.login(teacher);
            System.out.println("teacherList长度是：" + teacherList.size());
            if (teacherList.size() > 0 ) {
                List<Class> classList = classService.selectClassByTeacherId(userId);
                request.getSession().setAttribute("teacherId", userId);
                mav = new ModelAndView("/teacher/classList");
                mav.addObject("classList", classList);
                mav.addObject("teacherList", teacherList);
            } else {
                attr.addAttribute("message","账号名或密码错误");
                mav = new ModelAndView("redirect:/user/login.jsp");
            }
        } else if("admin".equals(userType)){
            Admin admin = new Admin();
            admin.setAdminId(userId);
            admin.setAdminPwd(pwd);
            List<Admin> adminList = adminService.login(admin);
            System.out.println("adminList长度是：" + adminList.size());
            if(adminList.size() > 0){
                mav = new ModelAndView("/admin/homePage");
                mav.addObject("adminList",adminList);
            } else {
                attr.addAttribute("message","账号名或密码错误");
                mav = new ModelAndView("redirect:/user/login.jsp");
            }
        }
        return mav;
    }

}
