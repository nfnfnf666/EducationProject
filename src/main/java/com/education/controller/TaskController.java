package com.education.controller;

import com.education.pojo.Completion;
import com.education.pojo.Notice;
import com.education.pojo.Student;
import com.education.pojo.Task;
import com.education.service.CompletionService;
import com.education.service.TaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    CompletionService completionService;

    @RequestMapping("taskPage")
    @ResponseBody
    public String taskPage(HttpServletRequest request,
                           @RequestParam(value = "limit" ,required = false) String pageSize,
                           @RequestParam(value ="page",required = false) String pageIndex){
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        System.out.println("taskPage.do:中从session中取得的courselistId值为："+(Integer) request.getSession().getAttribute("courselistId"));
        List<Task> taskList = taskService.selectAllTask((Integer) request.getSession().getAttribute("courselistId"));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());
        json.put("data", JSONArray.fromObject(taskList));

        return json.toString();
    }

    @RequestMapping("uploadTask")
    public String uploadTask(@RequestParam("taskId") Integer taskId, Model model, HttpServletRequest request){
        if(taskId!=null){
            Task task=taskService.queryTaskById(taskId);
            Completion completion = completionService.selectCompletionByTaskIdAndStduentId(taskId,(Integer) request.getSession().getAttribute("studentId"));
            model.addAttribute("task", task);
            model.addAttribute("completion", completion);
        }
        return "student/taskDetail";
    }

    @RequestMapping(value = "insertTask")
    @ResponseBody
    public String insertTask(Task task, HttpServletRequest request){
        task.setCourselistId((Integer) request.getSession().getAttribute("courselistId"));
        task.setTaskPublishedTime(new Date());
        int i=taskService.insert(task);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }

}
