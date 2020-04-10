package com.education.pojo;

public class Courselist {
    private Integer courselistId;

    private Integer classId;

    private Integer courseId;

    private Integer teacherId;

    public Courselist(Integer courselistId, Integer classId, Integer courseId, Integer teacherId) {
        this.courselistId = courselistId;
        this.classId = classId;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Courselist() {
        super();
    }

    public Integer getCourselistId() {
        return courselistId;
    }

    public void setCourselistId(Integer courselistId) {
        this.courselistId = courselistId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}