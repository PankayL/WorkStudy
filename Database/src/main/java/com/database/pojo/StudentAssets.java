package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:49
 */
public class StudentAssets {
    private String studentNo;
    private double assetsTotal;
    private int version;

    @Override
    public String toString() {
        return "StudentAssets{" +
                "studentNo='" + studentNo + '\'' +
                ", assetsTotal=" + assetsTotal +
                ", version=" + version +
                '}';
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public double getAssetsTotal() {
        return assetsTotal;
    }

    public void setAssetsTotal(double assetsTotal) {
        this.assetsTotal = assetsTotal;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public StudentAssets(String studentNo, double assetsTotal, int version) {
        this.studentNo = studentNo;
        this.assetsTotal = assetsTotal;
        this.version = version;
    }

    public StudentAssets() {
    }
}
