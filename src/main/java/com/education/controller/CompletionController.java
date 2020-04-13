package com.education.controller;

import com.education.pojo.Completion;
import com.education.pojo.CompletionVo;
import com.education.service.CompletionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("viewCompletionDetail")
    public String viewCompletionDetail(HttpServletRequest request,Integer taskId){
        request.getSession().setAttribute("taskId", taskId);
        return "teacher/completionDetail";
    }

    @RequestMapping("completionDetail")
    @ResponseBody
    public String completionDetail(HttpServletRequest request,
                                   @RequestParam(value = "limit" ,required = false) String pageSize,
                                   @RequestParam(value ="page",required = false) String pageIndex){
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        Integer taskId = (Integer) request.getSession().getAttribute("taskId");
        System.out.println("completionDetail.do中接收到的taskId值为："+taskId);
        List<CompletionVo> completionVoList = completionService.queryCompletionVoList(taskId);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());
        json.put("data", JSONArray.fromObject(completionVoList));
        return json.toString();
    }

}
