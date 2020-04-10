package com.education.controller;

import com.education.pojo.Class;
import com.education.service.ClassService;
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
public class ClassController {
    @Resource
    ClassService classService;

    /**
     * 查询班级信息
     *
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/classList.do")
    @ResponseBody
    public String classList(@RequestParam(value = "limit", required = false) String pageSize,
                            @RequestParam(value = "page", required = false) String pageIndex,
                            @RequestParam(value = "class_name", required = false) String className) {
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Class> classList = classService.selectClass(className);//调用service的查询方法
        for (int i = 0; i < classList.size(); i++) {
            System.out.println("班级：" + classList.get(i).getClassName());
        }
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());
        json.put("data", classList);

        return json.toString();
    }


    /*
     * @Description:插入数据
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    @RequestMapping("/insertClass.do")
    @ResponseBody
    public String insertClass(@RequestParam("classId") Integer classId,
                              @RequestParam("className") String className,
                              @RequestParam("classDesc") String classDesc) throws ParseException {
        Class record = new Class();
        record.setClassId(classId);
        record.setClassName(className);
        record.setClassDesc(classDesc);
//        System.out.println("class-->"+class);
        int i = classService.insert(record);//调用service层的插入方法

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
    @RequestMapping("/uploadClass.do")
    public String uploadClass(@RequestParam("classId") String classId, Model model) {
        if (classId != null) {
            Class record = classService.selectByPrimaryKey(Integer.parseInt(classId));

            model.addAttribute("record", record);
        }
        return "admin/uploadClass";
    }

    /*
     * @Description:修改班级信息
     * @param 修改后的数据
     * @return 返回影响条数
     */
    @RequestMapping("/updateClass.do")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam("classId") Integer classId,
                                     @RequestParam("className") String className,
                                     @RequestParam("classDesc") String classDesc) {

        Class record = new Class(classId, className, classDesc);
        int i = classService.updateClass( record);
        //新建json对象 并将参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);
        json.put("data", i);
        return json.toString();
    }

    /**
     * 根据ID删除班级
     * @param classId 班级ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delClass")
    @ResponseBody
    public String delClass(@RequestParam(value = "classId" ,required = false) String classId){
        System.out.println("delClass.do:classId:"+classId);
        int i=classService.delClass(Integer.parseInt(classId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }
    
}
