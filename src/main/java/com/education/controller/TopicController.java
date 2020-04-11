package com.education.controller;

import com.education.pojo.Reply;
import com.education.pojo.ReplyVo;
import com.education.pojo.Topic;
import com.education.pojo.TopicVo;
import com.education.service.ReplyService;
import com.education.service.TopicService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {
    @Resource
    TopicService topicService;
    @Resource
    ReplyService replyService;

    /**
     * 查询帖子信息
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/topicList.do")
    @ResponseBody
    public String topicList(@RequestParam(value = "limit", required = false) String pageSize,
                             @RequestParam(value = "page", required = false) String pageIndex,
                             @RequestParam(value = "topic_id",required = false) String topicId,
                            @RequestParam(value = "topic_title",required = false) String topicTitle,
                            @RequestParam(value = "topic_content",required = false) String topicContent){
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Topic> topicList= topicService.selectTopic(topicId, topicTitle, topicContent);//调用service的查询方法
        for (int i=0;i<topicList.size();i++){
            System.out.println("帖子："+topicList.get(i).getTopicId());
        }
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());
        json.put("data", topicList);

        return json.toString();
    }

    @RequestMapping("topicPage")
    public ModelAndView topicPage(@RequestParam("courselistId") Integer courselistId){
        ModelAndView mav = null;
        List<TopicVo> topicVoList = topicService.selectAllTopicVo(courselistId);
        mav = new ModelAndView("student/showTopic");
        mav.addObject("topicVoList", topicVoList);
        return mav;
    }

    @RequestMapping("teacherTopicPage")
    public ModelAndView teacherTopicPage(@RequestParam("courselistId") Integer courselistId){
        ModelAndView mav = null;
        List<TopicVo> topicVoList = topicService.selectAllTopicVo(courselistId);
        mav = new ModelAndView("teacher/topic");
        mav.addObject("topicVoList", topicVoList);
        return mav;
    }

    @RequestMapping("addTopic")
    public ModelAndView addTopic(HttpServletRequest request,
                                 @RequestParam("title") String topicTitle,
                                 @RequestParam("content") String topicContent){
        ModelAndView mav = null;
        Topic topic = new Topic();
        Integer publisherId = (Integer) request.getSession().getAttribute("studentId");
        if (publisherId == null){
            System.out.println("addTopic.do中的publisherId为空，登录者是老师");
            publisherId = (Integer) request.getSession().getAttribute("teacherId");
        }
        topic.setPublisherId(publisherId);
        topic.setCourselistId((Integer) request.getSession().getAttribute("courselistId"));
        topic.setTopicTitle(topicTitle);
        topic.setTopicContent(topicContent);
        topic.setTopicPublishedTime(new Date());
        topic.setReplyNumber(6);
        topicService.insertTopic(topic);
        List<TopicVo> topicVoList = topicService.selectAllTopicVo((Integer) request.getSession().getAttribute("courselistId"));
        if (request.getSession().getAttribute("studentId") != null){
            System.out.println("addTopic.do中的studentId不为空，返回学生讨论主页");
            mav = new ModelAndView("student/showTopic");
        } else {
            System.out.println("addTopic.do中的studentId是空的，返回教师讨论主页");
            mav = new ModelAndView("teacher/topic");
        }
        mav.addObject("topicVoList", topicVoList);
        return mav;
    }

    /**
     * 根据ID删除主题
     * @param topicId 主题ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delTopic")
    @ResponseBody
    public String delTopic(@RequestParam(value = "topicId" ,required = false) String topicId){

        int i=topicService.deleteTopicById(Integer.parseInt(topicId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 添加主题信息
     * @param topic 主题信息
     * @return
     */
    @RequestMapping(value = "insertTopic")
    @ResponseBody
    public String insertTopic(Topic topic){
        System.out.println(""+topic);
        int i=topicService.insertTopic(topic);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据主题ID查询主题信息，做修改的回显
     * @param topicId 主题ID
     * @param model 存放主题信息
     * @return
     */
    @RequestMapping(value = "uploadTopic")
    public String uploadTopic(@RequestParam("topicId") String topicId, Model model){
        //System.out.println("user_id"+user_id);
        if(topicId!=null){
            Topic topic=topicService.queryTopicById(Integer.parseInt(topicId));
            model.addAttribute("topic", topic);
        }
        return "admin/uploadTopic";
    }

    /**
     * 修改主题信息
     * @param topic 主题信息
     * @return
     */
    @RequestMapping(value = "updateTopic")
    @ResponseBody
    public String updateTopic(Topic topic){
        int i=topicService.update(topic);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    @RequestMapping(value = "topicDetail")
    public ModelAndView topicDetail(@RequestParam("topicId") Integer topicId){
        ModelAndView mav = null;
        TopicVo topicVo = topicService.queryTopicVoById(topicId);
        List<ReplyVo> replyVoList = replyService.selectReplyVoByTopicId(topicId);
        mav = new ModelAndView("student/topicDetail");
        mav.addObject("topicVo",topicVo);
        mav.addObject("replyVoList",replyVoList);
        return mav;
    }

    @RequestMapping(value = "topic")
    public ModelAndView topic(@RequestParam("topicId") Integer topicId){
        ModelAndView mav = null;
        TopicVo topicVo = topicService.queryTopicVoById(topicId);
        List<ReplyVo> replyVoList = replyService.selectReplyVoByTopicId(topicId);
        mav = new ModelAndView("teacher/reply");
        mav.addObject("topicVo",topicVo);
        mav.addObject("replyVoList",replyVoList);
        return mav;
    }

}
