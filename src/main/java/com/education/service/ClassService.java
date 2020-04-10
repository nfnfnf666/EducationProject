package com.education.service;

import com.education.dao.ClassMapper;
import com.education.pojo.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    public List<Class> selectClassByTeacherId(Integer teacherId){
        return classMapper.selectClassByTeacherId(teacherId);
    }

    /**
     * 查询所有班级数据信息
     * @return 返回class集合
     */
    public List<Class> selectClass(String className){
        return classMapper.selectClass(className);
    }

    /**
     * 根据主键删除数据
     * @param classId  需要删除的数据的主键
     * @return 返回影响的条数
     */
    public int delClass(Integer classId){
        return classMapper.deleteByPrimaryKey(classId);
    }

    /**
     * 添加数据
     * @param record 需要添加的数据
     * @return 返回影响的条数
     */
    public int insert(Class record){
        return classMapper.insertSelective(record);
    }

    /**
     * 根据主键查询
     * @param classId 需要查询的数据的主键
     * @return 返回class对象
     */
    public Class selectByPrimaryKey(Integer classId){
        return classMapper.selectByPrimaryKey(classId);
    }

    /**
     * 修改信息
     * @param record 修改后的信息封装的对象
     * @return
     */
    public int updateClass(Class record){
        return classMapper.updateByPrimaryKey(record);
    }

}
