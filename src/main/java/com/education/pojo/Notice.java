package com.education.pojo;

import java.util.Date;

public class Notice {
    private Integer noticeId;

    private Integer courselistId;

    private String noticeTitle;

    private Date noticePublishedTime;

    private String noticeContent;

    public Notice(Integer noticeId, Integer courselistId, String noticeTitle, Date noticePublishedTime, String noticeContent) {
        this.noticeId = noticeId;
        this.courselistId = courselistId;
        this.noticeTitle = noticeTitle;
        this.noticePublishedTime = noticePublishedTime;
        this.noticeContent = noticeContent;
    }

    public Notice() {
        super();
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getCourselistId() {
        return courselistId;
    }

    public void setCourselistId(Integer courselistId) {
        this.courselistId = courselistId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public Date getNoticePublishedTime() {
        return noticePublishedTime;
    }

    public void setNoticePublishedTime(Date noticePublishedTime) {
        this.noticePublishedTime = noticePublishedTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }
}