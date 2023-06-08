package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:29
 */
public class CollegeClass {
    private int classNo;
    private String className;
    private  CollegeMajor collegeMajor;

    @Override
    public String toString() {
        return "CollegeClass{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", collegeMajor=" + collegeMajor +
                '}';
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CollegeMajor getCollegeMajor() {
        return collegeMajor;
    }

    public void setCollegeMajor(CollegeMajor collegeMajor) {
        this.collegeMajor = collegeMajor;
    }

    public CollegeClass(int classNo, String className, CollegeMajor collegeMajor) {
        this.classNo = classNo;
        this.className = className;
        this.collegeMajor = collegeMajor;
    }

    public CollegeClass() {
    }
}
