package com.education.pojo;

public class CompletionVo {
    private Integer studentId;
    private String studentName;
    private String result;

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
        this.studentName = studentName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CompletionVo{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
