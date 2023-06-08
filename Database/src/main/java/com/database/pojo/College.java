package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:28
 */
public class College {
    private int collegeNo;
    private String collegeName;

    @Override
    public String toString() {
        return "College{" +
                "collegeNo=" + collegeNo +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }

    public int getCollegeNo() {
        return collegeNo;
    }

    public void setCollegeNo(int collegeNo) {
        this.collegeNo = collegeNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public College(int collegeNo, String collegeName) {
        this.collegeNo = collegeNo;
        this.collegeName = collegeName;
    }

    public College() {
    }
}
