package com.education.controller;

import com.education.pojo.Completion;
import com.education.service.CompletionService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CompletionController {
    @Resource
    CompletionService completionService;

    @RequestMapping("addCompletion")
    @ResponseBody
    public String addCompletion(HttpServletRequest request,
                                Model model,
                                @RequestParam("taskId") Integer taskId,
                                @RequestParam("completionContent") String completionContent){
        Completion completion = new Completion();
        completion.setCompletionContent(completionContent);
        completion.setStudentId((Integer) request.getSession().getAttribute("studentId"));
        completion.setTaskId(taskId);
        completion.setSubmissionTime(new Date());
        int i = completionService.insertCompletion(completion);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }

    @RequestMapping("completionDetail")
    @ResponseBody
    public String completionDetail(@RequestParam("taskId") Integer taskId){
        System.out.println("completionDetail.do中接收到的taskId值为："+taskId);

        /*select student.student_id,student.student_name
        from task,completion,student,courselist
        where task.task_id=100001 and task.courselist_id=courselist.courselist_id and
        courselist.class_id=student.class_id*/

        return "";
    }

}
