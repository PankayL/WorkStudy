package com.database.pojo;

import java.sql.Date;

/**
 * @author pankay
 * @create 2022-12-14-17:41
 */
public class Post {
    private int postNo;
    private Company company;
    private PostType postType;
    private String postTitle;
    private String postDetails;
    private Date startTime;
    private Date endTime;
    private int postState;
    private int deleted;
    private double postMoney;
    private int postCount;

    @Override
    public String toString() {
        return "Post{" +
                "postNo=" + postNo +
                ", company=" + company +
                ", postType=" + postType +
                ", postTitle='" + postTitle + '\'' +
                ", postDetails='" + postDetails + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", postState=" + postState +
                ", deleted=" + deleted +
                ", postMoney=" + postMoney +
                ", postCount=" + postCount +
                '}';
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public Post(int postNo, Company company, PostType postType, String postTitle, String postDetails, Date startTime, Date endTime, int postState, int deleted, double postMoney, int postCount) {
        this.postNo = postNo;
        this.company = company;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDetails = postDetails;
        this.startTime = startTime;
        this.endTime = endTime;
        this.postState = postState;
        this.deleted = deleted;
        this.postMoney = postMoney;
        this.postCount = postCount;
    }

    public double getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(double postMoney) {
        this.postMoney = postMoney;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPostState() {
        return postState;
    }

    public void setPostState(int postState) {
        this.postState = postState;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Post(int postNo, Company company, PostType postType, String postTitle, String postDetails, Date startTime, Date endTime, int postState, int deleted) {
        this.postNo = postNo;
        this.company = company;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDetails = postDetails;
        this.startTime = startTime;
        this.endTime = endTime;
        this.postState = postState;
        this.deleted = deleted;
    }

    public Post() {
    }
}
