package com.database.pojo;

import java.sql.Date;
import java.sql.Time;

/**
 * @author pankay
 * @create 2022-12-14-17:51
 */
public class StudentIncome {
    private int incomeNo;
    private Student student;
    private double incomeMoney;
    private Date time1;
    private Time time2;
    private int isFlag;
    private Company company;

    @Override
    public String toString() {
        return "StudentIncome{" +
                "incomeNo=" + incomeNo +
                ", student=" + student +
                ", incomeMoney=" + incomeMoney +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", isFlag=" + isFlag +
                ", company=" + company +
                '}';
    }

    public StudentIncome(int incomeNo, Student student, double incomeMoney, Date time1, Time time2, int isFlag, Company company) {
        this.incomeNo = incomeNo;
        this.student = student;
        this.incomeMoney = incomeMoney;
        this.time1 = time1;
        this.time2 = time2;
        this.isFlag = isFlag;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getIncomeNo() {
        return incomeNo;
    }

    public void setIncomeNo(int incomeNo) {
        this.incomeNo = incomeNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public int getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(int isFlag) {
        this.isFlag = isFlag;
    }

    public StudentIncome() {
    }
}
