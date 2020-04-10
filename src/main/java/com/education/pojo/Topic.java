package com.education.pojo;

import java.util.Date;

public class Topic {
    private Integer topicId;

    private Integer publisherId;

    private Integer courselistId;

    private String topicTitle;

    private String topicContent;

    private Date topicPublishedTime;

    private Integer replyNumber;

    public Topic(Integer topicId, Integer publisherId, Integer courselistId, String topicTitle, String topicContent, Date topicPublishedTime, Integer replyNumber) {
        this.topicId = topicId;
        this.publisherId = publisherId;
        this.courselistId = courselistId;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.topicPublishedTime = topicPublishedTime;
        this.replyNumber = replyNumber;
    }

    public Topic() {
        super();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getCourselistId() {
        return courselistId;
    }

    public void setCourselistId(Integer courselistId) {
        this.courselistId = courselistId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
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
        return "Topic{" +
                "topicId=" + topicId +
                ", publisherId=" + publisherId +
                ", courselistId=" + courselistId +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicPublishedTime=" + topicPublishedTime +
                ", replyNumber=" + replyNumber +
                '}';
    }
}