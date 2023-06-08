package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:34
 */
public class Manager {
    private int managerNo;
    private String managerPassword;
    private int deleted;

    @Override
    public String toString() {
        return "Manager{" +
                "managerNo=" + managerNo +
                ", managerPassword='" + managerPassword + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public int getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(int managerNo) {
        this.managerNo = managerNo;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Manager(int managerNo, String managerPassword, int deleted) {
        this.managerNo = managerNo;
        this.managerPassword = managerPassword;
        this.deleted = deleted;
    }

    public Manager() {
    }
}
