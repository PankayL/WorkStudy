package com.database.pojo;

import java.sql.Date;
import java.sql.Time;

/**
 * @author pankay
 * @create 2022-12-14-17:45
 */
public class PostEvaluate {
    private int evaluateNo;
    private int postNo;
    private String studentNo;
    private String evaluateDetails;
    private Date time1;
    private Time time2;
    private int deleted;

    @Override
    public String toString() {
        return "PostEvaluate{" +
                "evaluateNo=" + evaluateNo +
                ", postNo=" + postNo +
                ", studentNo='" + studentNo + '\'' +
                ", evaluateDetails='" + evaluateDetails + '\'' +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", deleted=" + deleted +
                '}';
    }

    public int getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(int evaluateNo) {
        this.evaluateNo = evaluateNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getEvaluateDetails() {
        return evaluateDetails;
    }

    public void setEvaluateDetails(String evaluateDetails) {
        this.evaluateDetails = evaluateDetails;
    }

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public PostEvaluate(int evaluateNo, int postNo, String studentNo, String evaluateDetails, Date time1, Time time2, int deleted) {
        this.evaluateNo = evaluateNo;
        this.postNo = postNo;
        this.studentNo = studentNo;
        this.evaluateDetails = evaluateDetails;
        this.time1 = time1;
        this.time2 = time2;
        this.deleted = deleted;
    }

    public PostEvaluate() {
    }
}
