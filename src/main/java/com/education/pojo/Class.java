package com.education.pojo;

public class Class {
    private Integer classId;

    private String className;

    private String classDesc;

    public Class(Integer classId, String className, String classDesc) {
        this.classId = classId;
        this.className = className;
        this.classDesc = classDesc;
    }

    public Class() {
        super();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc == null ? null : classDesc.trim();
    }
}