package com.education.pojo;

public class Student {
    private Integer studentId;

    private String studentName;

    private String studentPwd;

    private String studentSex;

    private Integer classId;

    private String studentImg;

    public Student(Integer studentId, String studentName, String studentPwd, String studentSex, Integer classId, String studentImg) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPwd = studentPwd;
        this.studentSex = studentSex;
        this.classId = classId;
        this.studentImg = studentImg;
    }

    public Student() {
        super();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentPwd() {
        return studentPwd;
    }

    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd == null ? null : studentPwd.trim();
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex == null ? null : studentSex.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg == null ? null : studentImg.trim();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentPwd='" + studentPwd + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", classId=" + classId +
                ", studentImg='" + studentImg + '\'' +
                '}';
    }
}