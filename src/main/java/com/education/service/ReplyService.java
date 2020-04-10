package com.education.service;

import com.education.dao.ReplyMapper;
import com.education.pojo.Reply;
import com.education.pojo.ReplyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {
    @Resource
    ReplyMapper replyMapper;

    /**
     * 查询回复信息
     * @param replyId  回复编号
     * @param replyerId 标题
     * @param replyContent 内容
     * @return
     */
    public List<Reply> selectReply(String replyId, String replyerId, String replyContent){
        Map<String,String> map=new HashMap();
        map.put("replyId", replyId);
        map.put("replyerId", replyerId);
        map.put("replyContent", replyContent);
        return replyMapper.selectReply(map);
    }

    /**
     * 根据回复ID删除回复
     * @param replyId 回复ID
     * @return 返回修改的记录数
     */
    public int deleteReplyById(int replyId){
        return replyMapper.deleteByPrimaryKey(replyId);
    }

    /**
     * 根据回复ID找回复
     * @param replyId 回复ID
     * @return 回复对象
     */
    public Reply queryReplyById(int replyId){
        return replyMapper.selectByPrimaryKey(replyId);
    }

    /**
     * 添加回复
     * @param reply 回复信息
     * @return 返回修改的记录数
     */
    public int insertReply(Reply reply){
        return replyMapper.insert(reply);
    }

    /**
     * 修改回复信息
     * @param reply 回复信息
     * @return
     */
    public int update(Reply reply){
        return replyMapper.updateByPrimaryKey(reply);
    }

    public List<ReplyVo> selectReplyVoByTopicId(Integer topicId){
        return replyMapper.selectReplyVoByTopicId(topicId);
    }

}
