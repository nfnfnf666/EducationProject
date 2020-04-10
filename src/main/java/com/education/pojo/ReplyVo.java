package com.education.pojo;

public class ReplyVo {
    private Reply reply;
    private Student student;

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ReplyVo{" +
                "reply=" + reply +
                ", student=" + student +
                '}';
    }
}
