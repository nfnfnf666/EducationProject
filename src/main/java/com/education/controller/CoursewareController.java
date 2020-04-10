package com.education.controller;

import com.education.pojo.Courseware;
import com.education.service.CoursewareService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping("uploadCourseware")
    public void uploadCourseware(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("coursewareName") String coursewareName,
                                 @RequestParam("coursewareType") String coursewareType,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("uploadTime") String uploadTime) throws IOException {
        // 上传文件
        ServletContext sctx = request.getServletContext();
        // 获得保存文件的路径
        String basePath = sctx.getRealPath("/upload/"+coursewareType);
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 截取文件格式
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        // 自定义方式产生文件名
        String serialName = String.valueOf(System.currentTimeMillis());
        // 待转码的文件
        String realPath = basePath + "/" + serialName + fileType;
        System.out.println("uploadCourseware.do: realPath" + realPath);
        File uploadFile = new File(realPath);
        // 保存文件
        Streams.copy(file.getInputStream(),new FileOutputStream(uploadFile.getAbsolutePath()),true);

    }

}
