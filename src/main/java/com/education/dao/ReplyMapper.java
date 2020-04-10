package com.education.dao;

import com.education.pojo.Reply;
import com.education.pojo.ReplyVo;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {

    /**
     * 查询回复信息
     *@param map  存放查询的条件
     * @return
     */
    List<Reply> selectReply(Map map);

    List<ReplyVo> selectReplyVoByTopicId(Integer topicId);

    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}