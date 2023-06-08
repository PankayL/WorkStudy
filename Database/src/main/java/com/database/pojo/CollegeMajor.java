package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:30
 */
public class CollegeMajor {
    private int majorNo;
    private String majorName;
    private College college;

    @Override
    public String toString() {
        return "CollegeMajor{" +
                "majorNo=" + majorNo +
                ", majorName='" + majorName + '\'' +
                ", college=" + college +
                '}';
    }

    public int getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(int majorNo) {
        this.majorNo = majorNo;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public CollegeMajor(int majorNo, String majorName, College college) {
        this.majorNo = majorNo;
        this.majorName = majorName;
        this.college = college;
    }

    public CollegeMajor() {
    }
}
