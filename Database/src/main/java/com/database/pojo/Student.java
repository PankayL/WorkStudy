package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:34
 */
public class Student {
    private String studentNo;
    private String studentPassword;
    private String studentName;
    private String studentSex;
    private CollegeClass collegeClass;
    private int deleted;

    @Override
    public String toString() {
        return "Student{" +
                "studentNo='" + studentNo + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", collegeClass=" + collegeClass +
                ", deleted=" + deleted +
                '}';
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public CollegeClass getCollegeClass() {
        return collegeClass;
    }

    public void setCollegeClass(CollegeClass collegeClass) {
        this.collegeClass = collegeClass;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Student(String studentNo, String studentPassword, String studentName, String studentSex, CollegeClass collegeClass, int deleted) {
        this.studentNo = studentNo;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.collegeClass = collegeClass;
        this.deleted = deleted;
    }

    public Student() {
    }
}
