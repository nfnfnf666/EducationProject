package com.education.pojo;

import com.education.pojo.Student;
import com.education.pojo.Topic;

public class TopicVo {
    private Student student;
    private Topic topic;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "TopicVo{" +
                "student=" + student +
                ", topic=" + topic +
                '}';
    }
}
