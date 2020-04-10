package com.education.controller;

import com.education.pojo.Courseware;
import com.education.service.CoursewareService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class CoursewareController {
    @Autowired
    CoursewareService coursewareService;

    @RequestMapping("videoCoursewarePage")
    public ModelAndView videoCoursewarePage(@RequestParam("courselistId") Integer courselistId){
        ModelAndView mav = null;
        List<Courseware> videoCoursewareList = coursewareService.selectAllVideoCourseware(courselistId);
        mav = new ModelAndView("student/showVideoCourseware");
        mav.addObject("videoCoursewareList", videoCoursewareList);
        return mav;
    }

    @RequestMapping("videoList")
    @ResponseBody
    public String videoList(HttpServletRequest request,
                            @RequestParam(value = "limit" ,required = false) String pageSize,
                            @RequestParam(value ="page",required = false) String pageIndex){
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        Integer courselistId = (Integer) request.getSession().getAttribute("courselistId");
        List<Courseware> videoCoursewareList = coursewareService.selectAllVideoCourseware(courselistId);

        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());
        json.put("data", JSONArray.fromObject(videoCoursewareList));
        return json.toString();
    }

    @RequestMapping("documentCoursewarePage")
    public ModelAndView documentCoursewarePage(@RequestParam("courselistId") Integer courselistId){
        ModelAndView mav = null;
        List<Courseware> documentCoursewareList = coursewareService.selectAllDocumentCourseware(courselistId);
        mav = new ModelAndView("student/showDocumentCourseware");
        mav.addObject("documentCoursewareList", documentCoursewareList);
        return mav;
    }

    @RequestMapping("documentList")
    @ResponseBody
    public String documentList(HttpServletRequest request,
                                     @RequestParam(value = "limit" ,required = false) String pageSize,
                                     @RequestParam(value ="page",required = false) String pageIndex){
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        Integer courselistId = (Integer) request.getSession().getAttribute("courselistId");
        List<Courseware> documentCoursewareList = coursewareService.selectAllDocumentCourseware(courselistId);

        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());
        json.put("data", JSONArray.fromObject(documentCoursewareList));
        return json.toString();
    }

    @RequestMapping("play")
    public ModelAndView play(Integer coursewareId) {
        ModelAndView mav = null;
        Courseware courseware = coursewareService.selectCoursewareById(coursewareId);
        mav = new ModelAndView("student/videoDetail");
        mav.addObject("courseware", courseware);
        return mav;
    }

    @RequestMapping("playVideo")
    public ModelAndView playVideo(Integer coursewareId) {
        ModelAndView mav = null;
        Courseware courseware = coursewareService.selectCoursewareById(coursewareId);
        mav = new ModelAndView("teacher/playVideo");
        mav.addObject("courseware", courseware);
        return mav;
    }

    @RequestMapping("viewDocument")
    public ModelAndView viewDocument(Integer coursewareId) {
        ModelAndView mav = null;
        Courseware courseware = coursewareService.selectCoursewareById(coursewareId);
        mav = new ModelAndView("teacher/viewDocument");
        mav.addObject("courseware", courseware);
        return mav;
    }

    @RequestMapping(value = "delCourseware")
    @ResponseBody
    public String delCourseware(@RequestParam(value = "coursewareId" ,required = false) Integer coursewareId){
        int i=coursewareService.deleteCoursewareById(coursewareId);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        json.put("data",i);
        return json.toString();
    }

    @RequestMapping("uploadVideo")
    @ResponseBody
    public String uploadVideo(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        System.out.println("uploadVideo.do:中的request.getServletContext().getRealPath()" + request.getServletContext().getRealPath("/courseware/video/"));
        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        String newName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        String videoPath = request.getServletContext().getRealPath("/courseware/video/") + newName;
        //将文件上传到本地
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(videoPath));
        JSONObject json2 = new JSONObject();
        json2.put("src", "courseware/video/" + newName);
        json.put("data", json2);
        return json.toString();
    }

    @RequestMapping(value = "insertVideo")
    @ResponseBody
    public String insertVideo(Courseware courseware, HttpServletRequest request){
        courseware.setCourselistId((Integer) request.getSession().getAttribute("courselistId"));
        courseware.setCoursewareType("video");
        courseware.setUploadTime(new Date());
        int i=coursewareService.insertCourseware(courseware);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    @RequestMapping("uploadDocument")
    @ResponseBody
    public String uploadDocument(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        System.out.println("uploadDocument.do:中的request.getServletContext().getRealPath()" + request.getServletContext().getRealPath("/courseware/document/"));
        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        String newName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        String documentPath = request.getServletContext().getRealPath("/courseware/document/") + newName;
        //将文件上传到本地
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(documentPath));
        JSONObject json2 = new JSONObject();
        json2.put("src", "courseware/document/" + newName);
        json.put("data", json2);
        return json.toString();
    }

    @RequestMapping(value = "insertDocument")
    @ResponseBody
    public String insertDocument(Courseware courseware, HttpServletRequest request){
        courseware.setCourselistId((Integer) request.getSession().getAttribute("courselistId"));
        courseware.setCoursewareType("document");
        courseware.setUploadTime(new Date());
        int i=coursewareService.insertCourseware(courseware);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

}
