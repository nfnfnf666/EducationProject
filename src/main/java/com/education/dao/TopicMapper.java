package com.education.dao;

import com.education.pojo.Topic;
import com.education.pojo.TopicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TopicMapper {

    /**
     * 查询主题信息
     *@param map  存放查询的条件
     * @return
     */
    List<Topic> selectTopic(Map map);

    List<TopicVo> selectAllTopicVo(@Param("courselistId") Integer courselistId);

    TopicVo queryTopicVoById(Integer topicId);

    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}