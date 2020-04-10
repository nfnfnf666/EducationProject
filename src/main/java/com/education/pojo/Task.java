package com.education.pojo;

import java.util.Date;

public class Task {
    private Integer taskId;

    private Integer courselistId;

    private String taskTitle;

    private Date taskPublishedTime;

    private String taskContent;

    public Task(Integer taskId, Integer courselistId, String taskTitle, Date taskPublishedTime, String taskContent) {
        this.taskId = taskId;
        this.courselistId = courselistId;
        this.taskTitle = taskTitle;
        this.taskPublishedTime = taskPublishedTime;
        this.taskContent = taskContent;
    }

    public Task() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCourselistId() {
        return courselistId;
    }

    public void setCourselistId(Integer courselistId) {
        this.courselistId = courselistId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle == null ? null : taskTitle.trim();
    }

    public Date getTaskPublishedTime() {
        return taskPublishedTime;
    }

    public void setTaskPublishedTime(Date taskPublishedTime) {
        this.taskPublishedTime = taskPublishedTime;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }
}