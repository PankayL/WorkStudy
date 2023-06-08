package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:34
 */
public class Company {
    private String companyNo;
    private String companyPassword;
    private int deleted;
    private CompanyType companyType;
    private String companyPeople;
    private String companyName;

    @Override
    public String toString() {
        return "Company{" +
                "companyNo='" + companyNo + '\'' +
                ", companyPassword='" + companyPassword + '\'' +
                ", deleted=" + deleted +
                ", companyType=" + companyType +
                ", companyPeople='" + companyPeople + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String getCompanyPeople() {
        return companyPeople;
    }

    public void setCompanyPeople(String companyPeople) {
        this.companyPeople = companyPeople;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Company(String companyNo, String companyPassword, int deleted, CompanyType companyType, String companyPeople, String companyName) {
        this.companyNo = companyNo;
        this.companyPassword = companyPassword;
        this.deleted = deleted;
        this.companyType = companyType;
        this.companyPeople = companyPeople;
        this.companyName = companyName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Company(String companyNo, String companyPassword, int deleted, CompanyType companyType) {
        this.companyNo = companyNo;
        this.companyPassword = companyPassword;
        this.deleted = deleted;
        this.companyType = companyType;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Company(String companyNo, String companyPassword, int deleted) {
        this.companyNo = companyNo;
        this.companyPassword = companyPassword;
        this.deleted = deleted;
    }

    public Company() {
    }
}
