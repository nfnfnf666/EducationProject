package com.education.controller;

import com.education.pojo.Reply;
import com.education.pojo.ReplyVo;
import com.education.pojo.Student;
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
public class ReplyController {
    @Resource
    ReplyService replyService;
    @Resource
    TopicService topicService;
    /**
     * 查询帖子信息
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/replyList.do")
    @ResponseBody
    public String replyList(@RequestParam(value = "limit", required = false) String pageSize,
                            @RequestParam(value = "page", required = false) String pageIndex,
                            @RequestParam(value = "reply_id",required = false) String replyId,
                            @RequestParam(value = "replyer_id",required = false) String replyerId,
                            @RequestParam(value = "reply_content",required = false) String replyContent){
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Reply> replyList= replyService.selectReply(replyId, replyerId, replyContent);//调用service的查询方法
        for (int i=0;i<replyList.size();i++){
            System.out.println("回复："+replyList.get(i).getReplyId());
        }
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());
        json.put("data", replyList);

        return json.toString();
    }

    @RequestMapping("addReply")
    public ModelAndView addReply(HttpServletRequest request,
                                 @RequestParam("topicId") Integer topicId,
                                 @RequestParam("content") String content){
        ModelAndView mav = null;
        Reply reply = new Reply();
        reply.setTopicId(topicId);
        reply.setReplyerId((Integer) request.getSession().getAttribute("studentId"));
        System.out.println("addReply.do中的replyerId的值为："+(Integer) request.getSession().getAttribute("studentId"));
        reply.setReplyContent(content);
        reply.setReplyTime(new Date());
        replyService.insertReply(reply);

        TopicVo topicVo = topicService.queryTopicVoById(topicId);
        List<ReplyVo> replyVoList = replyService.selectReplyVoByTopicId(topicId);
        mav = new ModelAndView("student/topicDetail");
        mav.addObject("topicVo",topicVo);
        mav.addObject("replyVoList",replyVoList);
        return mav;
    }

    @RequestMapping("addTeacherReply")
    public ModelAndView addTeacherReply(HttpServletRequest request,
                                 @RequestParam("topicId") Integer topicId,
                                 @RequestParam("content") String content){
        ModelAndView mav = null;
        Reply reply = new Reply();
        reply.setTopicId(topicId);
        reply.setReplyerId((Integer) request.getSession().getAttribute("teacherId"));
        reply.setReplyContent(content);
        reply.setReplyTime(new Date());
        replyService.insertReply(reply);

        TopicVo topicVo = topicService.queryTopicVoById(topicId);
        List<ReplyVo> replyVoList = replyService.selectReplyVoByTopicId(topicId);
        mav = new ModelAndView("teacher/reply");
        mav.addObject("topicVo",topicVo);
        mav.addObject("replyVoList",replyVoList);
        return mav;
    }


    /**
     * 根据ID删除回复
     * @param replyId 回复ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delReply")
    @ResponseBody
    public String delReply(@RequestParam(value = "replyId" ,required = false) String replyId){

        int i=replyService.deleteReplyById(Integer.parseInt(replyId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 添加回复信息
     * @param reply 回复信息
     * @return
     */
    @RequestMapping(value = "insertReply")
    @ResponseBody
    public String insertReply(Reply reply){
        System.out.println(""+reply);
        int i=replyService.insertReply(reply);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据回复ID查询回复信息，做修改的回显
     * @param replyId 回复ID
     * @param model 存放回复信息
     * @return
     */
    @RequestMapping(value = "uploadReply")
    public String uploadReply(@RequestParam("replyId") String replyId, Model model){
        //System.out.println("user_id"+user_id);
        if(replyId!=null){
            Reply reply=replyService.queryReplyById(Integer.parseInt(replyId));
            model.addAttribute("reply", reply);
            System.out.println("upload-->" + reply);
        }
        return "admin/uploadReply";
    }

    /**
     * 修改回复信息
     * @param reply 回复信息
     * @return
     */
    @RequestMapping(value = "updateReply")
    @ResponseBody
    public String updateReply(Reply reply){
        int i=replyService.update(reply);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

}
