package com.education.service;

import com.education.dao.TopicMapper;
import com.education.pojo.Topic;
import com.education.pojo.TopicVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {
    @Resource
    TopicMapper topicMapper;

    /**
     * 查询主题信息
     * @param topicId  主题编号
     * @param topicTitle 标题
     * @param topicContent 内容
     * @return
     */
    public List<Topic> selectTopic(String topicId, String topicTitle, String topicContent){
        Map<String,String> map=new HashMap();
        map.put("topicId", topicId);
        map.put("topicTitle", topicTitle);
        map.put("topicContent", topicContent);
        return topicMapper.selectTopic(map);
    }

    public List<TopicVo> selectAllTopicVo(Integer courselistId){
        return topicMapper.selectAllTopicVo(courselistId);
    }

    public TopicVo queryTopicVoById(Integer topicId){
        return topicMapper.queryTopicVoById(topicId);
    }

    /**
     * 根据主题ID删除主题
     * @param topicId 主题ID
     * @return 返回修改的记录数
     */
    public int deleteTopicById(int topicId){
        return topicMapper.deleteByPrimaryKey(topicId);
    }

    /**
     * 根据主题ID找主题
     * @param topicId 主题ID
     * @return 主题对象
     */
    public Topic queryTopicById(int topicId){
        return topicMapper.selectByPrimaryKey(topicId);
    }

    /**
     * 添加主题
     * @param topic 主题信息
     * @return 返回修改的记录数
     */
    public int insertTopic(Topic topic){
        return topicMapper.insert(topic);
    }

    /**
     * 修改主题信息
     * @param topic 主题信息
     * @return
     */
    public int update(Topic topic){
        return topicMapper.updateByPrimaryKey(topic);
    }


}
