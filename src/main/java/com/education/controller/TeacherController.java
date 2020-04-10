package com.education.controller;

import com.education.pojo.Teacher;
import com.education.service.TeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    /**
     *分页查询所有教师信息
     * @param pageSize 每页记录数
     * @param pageIndex 页码下标
     * @param teacherId 教师ID
     * @param teacherName 教师名称，组合查询
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "teacherPage")
    @ResponseBody
    public String teacherPage(@RequestParam(value = "limit" ,required = false) String pageSize,
                              @RequestParam(value ="page",required = false) String pageIndex,
                              @RequestParam(value ="teacher_id",required = false) String teacherId,
                              @RequestParam(value ="teacher_name",required = false) String teacherName){
//        System.out.println("pageIndex"+pageIndex);
        //分页查询所有教师信息
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<Teacher> teacherList = teacherService.queryTeacherList(teacherId, teacherName);
        for (int i=0;i<teacherList.size();i++){
            System.out.println("教师："+teacherList.get(i).getTeacherName());
        }

        JSONObject json=new JSONObject();

        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());

        json.put("data", JSONArray.fromObject(teacherList));

        return json.toString();
    }

    /**
     * 批量删除教师
     * @param ids
     * @return
     */
    @RequestMapping(value = "delTeachers.do")
    @ResponseBody
    public String delTeachers(@RequestParam("ids") String ids){
        String[] teacherIds=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<teacherIds.length;i++){
            if(teacherIds[i]!=null){
                teacherService.deleteTeacherById(Integer.parseInt(teacherIds[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

    /**
     * 根据ID删除教师
     * @param teacherId 教师ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delTeacher")
    @ResponseBody
    public String delTeacher(@RequestParam(value = "teacherId" ,required = false) String teacherId){
//        if(userid!=null){ }
//        return "users";
        int i=teacherService.deleteTeacherById(Integer.parseInt(teacherId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 添加教师信息
     * @param teacher 教师信息
     * @return
     */
    @RequestMapping(value = "insertTeacher")
    @ResponseBody
    public String insertTeacher(Teacher teacher){
        System.out.println(""+teacher);
        int i=teacherService.insertTeacher(teacher);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据教师ID查询教师信息，做修改的回显
     * @param teacherId 教师ID
     * @param model 存放教师信息
     * @return
     */
    @RequestMapping(value = "uploadTeacher")
    public String uploadTeacher(@RequestParam("teacherId") String teacherId, Model model){
        //System.out.println("user_id"+user_id);
        if(teacherId!=null){
            Teacher teacher=teacherService.queryTeacherById(Integer.parseInt(teacherId));
            model.addAttribute("teacher", teacher);
            System.out.println("upload-->" + teacher);
        }
        return "admin/uploadTeacher";
    }

    /**
     * 修改教师信息
     * @param teacher 教师信息
     * @return
     */
    @RequestMapping(value = "updateTeacher")
    @ResponseBody
    public String updateTeacher(Teacher teacher){
        int i=teacherService.update(teacher);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 批量导入数据
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "teacherUploadExcel")
    @ResponseBody
    public String teacherUploadExcel(@RequestParam MultipartFile file) throws Exception{

        //System.out.println("request-->"+request);
        //System.out.println("file-->"+file);
        String fileOldName = null;
        String fileNewName = null;

        //判断文件是否为空
        if(file.isEmpty()){
            return null;
        }
        fileOldName=file.getOriginalFilename();
        //System.out.println("fileOldName-->"+fileOldName);

        //将文件更改一个新名字
        fileNewName= UUID.randomUUID().toString()+"_"+fileOldName;
        System.out.println("fileNewName-->"+fileNewName);
        int i=0;
        try {
            //将文件上传到本地
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File("D:\\Excel\\"+fileNewName));

            String path="D:\\Excel\\"+fileNewName;
            //path="D:\\WPS\\data\\工作簿1.xlsx";
            List<String> list = read(path);

            for(String s: list){
                //System.out.println(s);
                String[] str=s.split(";");
                Teacher teacher=new Teacher();
                teacher.setTeacherId(Integer.parseInt(str[0]));
                teacher.setTeacherName(str[1]);
                teacher.setTeacherImg(str[2]);
                teacher.setTeacherSex(str[3]);
                teacher.setTeacherPwd(str[4]);
                teacher.setTeacherPhone(str[5]);
                teacher.setTeacherEmail(str[6]);

                //调用service层的增加教师方法
                i=teacherService.insertTeacher(teacher);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json=new JSONObject();
        json.put("code",i);
        json.put("msg","");
        json.put("count",1);
        return json.toString();
    }

    //将Excel数据封装成String类型的List
    private List<String> read(String path) {
        String s="";
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        //判断文件是否存在
        if (!file.exists()) {
            System.out.println("文件不存在");
        }
        try {
            //新建一个Excel2007之前用HSSFWorkbook，其格式为 .xsl
            //新建一个Excel2007之后用XSSFWorkbook，其格式为 .xslx
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum() + 1;
            // System.out.println("rowLength-->"+rowLength);
            //工作表的列
            Row row = sheet.getRow(0);
            //System.out.println("row-->"+row);
            //总列数
            int colLength = row.getLastCellNum();
            //System.out.println("colLength-->"+colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            //System.out.println("cell-->"+cell);
            //得到单元格样式
            //CellStyle cellStyle = cell.getCellStyle();
            for (int i = 1; i < rowLength; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //System.out.println("cell2-->"+cell);
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                    //Cannot get a STRING value from a NUMERIC cell
                    //将所有的需要读的Cell表格设置为String格式
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        s+=cell.getStringCellValue()+";";
                    }else {
                        s+=""+";";
                    }


                }
                list.add(s);
                s="";
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        //返回这个集合
        return list;
    }

    /**
     * 导出数据到Excel
     * @return
     */
    @RequestMapping(value = "teacherDownloadExcel")
    @ResponseBody
    public String teacherDownloadExcel(@RequestParam("ids") String ids){
        System.out.println("ids-->"+ids);
        String[] teacherIds=ids.split(",");

        String[] title ={"工号","姓名","头像","性别","密码","手机号","邮箱"};

        File fi=new File("D:/Excel/"+ new Date().getTime() +"_teacherData.xlsx");

        //创建Excel工作簿 office版本2007及以上
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建一个工作表
        XSSFSheet sheet=workbook.createSheet();
        //创建第一行
        XSSFRow row=sheet.createRow(0);

        XSSFCell cell=null;//列

        for (int i = 0; i < title.length; i++) {
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }

        for (int i=0;i<teacherIds.length;i++){
            if(teacherIds[i]!=null){
                Teacher teacher = teacherService.queryTeacherById(Integer.parseInt(teacherIds[i]));
                //System.out.println("user-->"+user);
                //追加数据

                XSSFRow nextrow = sheet.createRow(i+1);

                XSSFCell cell2 = nextrow.createCell(0);
                cell2.setCellValue(teacher.getTeacherId());

                cell2=nextrow.createCell(1);
                cell2.setCellValue(teacher.getTeacherName());

                cell2=nextrow.createCell(2);
                cell2.setCellValue(teacher.getTeacherImg());

                cell2=nextrow.createCell(3);
                cell2.setCellValue(teacher.getTeacherSex());

                cell2=nextrow.createCell(4);
                cell2.setCellValue(teacher.getTeacherPwd());

                cell2=nextrow.createCell(5);
                cell2.setCellValue(teacher.getTeacherPhone());

                cell2=nextrow.createCell(6);
                cell2.setCellValue(teacher.getTeacherEmail());

                //String date= sdf.format(kompanios.get(i).getK_date().getTime());
                //cell2.setCellValue(date);
                //cell2=nextrow.createCell(9);
                // 日期格式转为字符串输出
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //String time= sdf.format(kompanios.get(i).getK_begintime().getTime());
                //cell2.setCellValue(time);
                //cell2=nextrow.createCell(10);

                //String time1= sdf.format(kompanios.get(i).getK_endtime().getTime());
                //cell2.setCellValue(time1);
                //cell2=nextrow.createCell(11);
            }
        }

        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        try {
            fi.createNewFile();
            FileOutputStream stream= FileUtils.openOutputStream(fi);
            workbook.write(stream);
            stream.close();
            json.put("code",1);

            //获取Excel保存路径
            String path = fi.getAbsolutePath();
            json.put("excelPath",path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    /**
     * 修改头像
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "teacherUploadImage")
    @ResponseBody
    public String teacherUploadImage(@RequestParam MultipartFile file) throws Exception{
        if(file.isEmpty()){
            return null;
        }
        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        String newName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        String imagePath = "E:\\IdeaProjects\\EducationProject\\src\\main\\webapp\\image\\" + newName;
        //将文件上传到本地
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imagePath));
//        System.out.println("imagePath:" + imagePath);
        JSONObject json2 = new JSONObject();
        json2.put("src", newName);
        json.put("data", json2);
//        System.out.println("image.json:" + json.toString());

        return json.toString();
    }


}
