package com.education.controller;

import com.education.pojo.Notice;
import com.education.service.NoticeService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @RequestMapping("noticePage")
    public ModelAndView noticePage(Integer courselistId){
        ModelAndView mav = null;
        List<Notice> noticeList = noticeService.selectAllNotice(courselistId);
        mav = new ModelAndView("student/showNotice");
        mav.addObject("noticeList", noticeList);
        return mav;
    }

    @RequestMapping("noticeList")
    @ResponseBody
    public String noticeList(HttpServletRequest request,
                             @RequestParam(value = "limit" ,required = false) String pageSize,
                             @RequestParam(value ="page",required = false) String pageIndex,
                             @RequestParam(value ="notice_title",required = false) String noticeTitle,
                             @RequestParam(value ="notice_content",required = false) String noticeContent){
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<Notice> noticeList = noticeService.selectNotices(String.valueOf((Integer) request.getSession().getAttribute("courselistId")), noticeTitle, noticeContent);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());
        json.put("data", JSONArray.fromObject(noticeList));
        return json.toString();
    }

    @RequestMapping(value = "delNotice")
    @ResponseBody
    public String delNotice(@RequestParam(value = "noticeId" ,required = false) Integer noticeId){
        int i = noticeService.deleteNoticeById(noticeId);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }

    @RequestMapping(value = "uploadNotice")
    public String uploadNotice(@RequestParam("noticeId") String noticeId, Model model){
        System.out.println("uploadNotice:  noticeId:"+noticeId);
        if(noticeId!=null){
            Notice notice=noticeService.queryNoticeById(Integer.parseInt(noticeId));
            model.addAttribute("notice", notice);
        }
        return "teacher/uploadNotice";
    }

    @RequestMapping(value = "updateNotice")
    @ResponseBody
    public String updateNotice(Notice notice){
        int i=noticeService.update(notice);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }
    
    @RequestMapping(value = "insertNotice")
    @ResponseBody
    public String insertNotice(Notice notice, HttpServletRequest request){
        notice.setCourselistId((Integer) request.getSession().getAttribute("courselistId"));
        notice.setNoticePublishedTime(new Date());
        int i=noticeService.insert(notice);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }
    
}
