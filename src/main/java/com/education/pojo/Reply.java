package com.education.pojo;

import java.util.Date;

public class Reply {
    private Integer replyId;

    private Integer topicId;

    private Integer replyerId;

    private String replyContent;

    private Date replyTime;

    public Reply(Integer replyId, Integer topicId, Integer replyerId, String replyContent, Date replyTime) {
        this.replyId = replyId;
        this.topicId = topicId;
        this.replyerId = replyerId;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

    public Reply() {
        super();
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}