package com.education.controller;

import com.education.pojo.Course;
import com.education.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Controller
public class CourseController {
    @Resource
    CourseService courseService;

    /**
     * 查询课程信息
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/courseList.do")
    @ResponseBody
    public String courseList(@RequestParam(value = "limit", required = false) String pageSize,
                             @RequestParam(value = "page", required = false) String pageIndex,
                             @RequestParam(value = "course_name",required = false) String courseName){
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Course> courseList= courseService.selectCourse(courseName);//调用service的查询方法
        for (int i=0;i<courseList.size();i++){
            System.out.println("课程："+courseList.get(i).getCourseName());
        }
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());
        json.put("data", courseList);

        return json.toString();
    }



    /*
     * @Description:插入数据
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    @RequestMapping("/insertCourse.do")
    @ResponseBody
    public String insertCourse(@RequestParam("courseId") Integer courseId,
                               @RequestParam("courseName") String courseName,
                               @RequestParam("courseDesc") String courseDesc) {
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseDesc(courseDesc);
//        System.out.println("course-->"+course);
        int i=courseService.insert(course);//调用service层的插入方法

        //新建json对象并将一系列参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);

        json.put("data", i);
        return json.toString();
    }

    /*
     * @Description:根据id查询数据信息 做回显使用
     * @param  需要查询的数据的主键
     * @return 返回至页面
     */
    @RequestMapping("/uploadCourse.do")
    public String uploadCourse(@RequestParam("courseId") String courseId, Model model) {
        if (courseId != null) {
            Course course = courseService.selectByPrimaryKey(Integer.parseInt(courseId));

            //时区日期转换
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            String time=sdf.format(course.getOnlineDate().getTime());
//            Date online=sdf.parse(time);

            model.addAttribute("course", course);
        }
        return "admin/uploadCourse";
    }

    /*
     * @Description:修改课程信息
     * @param 修改后的数据
     * @return 返回影响条数
     */
    @RequestMapping("/updateCourse.do")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam("courseId") Integer courseId,
                                     @RequestParam("courseName") String courseName,
                                     @RequestParam("courseDesc") String courseDesc) {

        Course course = new Course(courseId, courseName, courseDesc);
        int i = courseService.updateCourse(course);
        //新建json对象 并将参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);
        json.put("data", i);
        return json.toString();
    }

    /**
     * 根据ID删除课程
     * @param courseId 课程ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delCourse")
    @ResponseBody
    public String delCourse(@RequestParam(value = "courseId" ,required = false) String courseId){
        System.out.println("delCourse.do:courseId:"+courseId);
        int i=courseService.delCourse(Integer.parseInt(courseId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }

}
