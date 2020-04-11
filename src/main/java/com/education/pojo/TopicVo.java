package com.education.pojo;

import com.education.pojo.Student;
import com.education.pojo.Topic;

import java.util.Date;

public class TopicVo {
    private String name;
    private String img;
    private Integer topicId;
    private String topicTitle;
    private String topicContent;
    private Date topicPublishedTime;
    private Integer replyNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Date getTopicPublishedTime() {
        return topicPublishedTime;
    }

    public void setTopicPublishedTime(Date topicPublishedTime) {
        this.topicPublishedTime = topicPublishedTime;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    @Override
    public String toString() {
        return "TopicVo{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", topicId=" + topicId +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicPublishedTime=" + topicPublishedTime +
                ", replyNumber=" + replyNumber +
                '}';
    }
}
