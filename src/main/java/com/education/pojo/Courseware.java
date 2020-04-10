package com.education.pojo;

import java.util.Date;

public class Courseware {
    private Integer coursewareId;

    private String coursewareName;

    private String coursewareType;

    private String coursewareUrl;

    private Integer courselistId;

    private Date uploadTime;

    public Courseware(Integer coursewareId, String coursewareName, String coursewareType, String coursewareUrl, Integer courselistId, Date uploadTime) {
        this.coursewareId = coursewareId;
        this.coursewareName = coursewareName;
        this.coursewareType = coursewareType;
        this.coursewareUrl = coursewareUrl;
        this.courselistId = courselistId;
        this.uploadTime = uploadTime;
    }

    public Courseware() {
        super();
    }

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName == null ? null : coursewareName.trim();
    }

    public String getCoursewareType() {
        return coursewareType;
    }

    public void setCoursewareType(String coursewareType) {
        this.coursewareType = coursewareType == null ? null : coursewareType.trim();
    }

    public String getCoursewareUrl() {
        return coursewareUrl;
    }

    public void setCoursewareUrl(String coursewareUrl) {
        this.coursewareUrl = coursewareUrl == null ? null : coursewareUrl.trim();
    }

    public Integer getCourselistId() {
        return courselistId;
    }

    public void setCourselistId(Integer courselistId) {
        this.courselistId = courselistId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}