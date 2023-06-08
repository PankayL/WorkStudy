package com.database.pojo;

import java.sql.Date;
import java.sql.Time;

/**
 * @author pankay
 * @create 2022-12-14-17:54
 */
public class StudentResume {
    private Student student;
    private String resumeDetails;
    private Post post;
    private int resumeState;
    private int resumeNo;
    private Date time;
    private Time time1;
    private int deleted;

    @Override
    public String toString() {
        return "StudentResume{" +
                "student=" + student +
                ", resumeDetails='" + resumeDetails + '\'' +
                ", post=" + post +
                ", resumeState=" + resumeState +
                ", resumeNo=" + resumeNo +
                ", time=" + time +
                ", time1=" + time1 +
                ", deleted=" + deleted +
                '}';
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public StudentResume(Student student, String resumeDetails, Post post, int resumeState, int resumeNo, Date time, Time time1, int deleted) {
        this.student = student;
        this.resumeDetails = resumeDetails;
        this.post = post;
        this.resumeState = resumeState;
        this.resumeNo = resumeNo;
        this.time = time;
        this.time1 = time1;
        this.deleted = deleted;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getResumeDetails() {
        return resumeDetails;
    }

    public void setResumeDetails(String resumeDetails) {
        this.resumeDetails = resumeDetails;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getResumeState() {
        return resumeState;
    }

    public void setResumeState(int resumeState) {
        this.resumeState = resumeState;
    }

    public int getResumeNo() {
        return resumeNo;
    }

    public void setResumeNo(int resumeNo) {
        this.resumeNo = resumeNo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public StudentResume(Student student, String resumeDetails, Post post, int resumeState, int resumeNo, Date time, Time time1) {
        this.student = student;
        this.resumeDetails = resumeDetails;
        this.post = post;
        this.resumeState = resumeState;
        this.resumeNo = resumeNo;
        this.time = time;
        this.time1 = time1;
    }

    public StudentResume() {
    }
}
