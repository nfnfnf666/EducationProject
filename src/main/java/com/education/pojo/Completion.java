package com.education.pojo;

import java.util.Date;

public class Completion {
    private Integer completionId;

    private Integer taskId;

    private Integer studentId;

    private Date submissionTime;

    private String completionScore;

    private String completionContent;

    public Completion(Integer completionId, Integer taskId, Integer studentId, Date submissionTime, String completionScore, String completionContent) {
        this.completionId = completionId;
        this.taskId = taskId;
        this.studentId = studentId;
        this.submissionTime = submissionTime;
        this.completionScore = completionScore;
        this.completionContent = completionContent;
    }

    public Completion() {
        super();
    }

    public Integer getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Integer completionId) {
        this.completionId = completionId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getCompletionScore() {
        return completionScore;
    }

    public void setCompletionScore(String completionScore) {
        this.completionScore = completionScore == null ? null : completionScore.trim();
    }

    public String getCompletionContent() {
        return completionContent;
    }

    public void setCompletionContent(String completionContent) {
        this.completionContent = completionContent == null ? null : completionContent.trim();
    }
}