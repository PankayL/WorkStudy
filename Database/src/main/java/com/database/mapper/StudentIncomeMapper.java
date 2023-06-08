package com.database.mapper;

import com.database.pojo.StudentIncome;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface StudentIncomeMapper {
    // 获取明细总数
    int getStudentIncomeTotal(String studentNo, String isFlag);
    // 获取明细列表
    List<StudentIncome> getStudentIncomeListLimit(int pageNum, int pageSize, String studentNo, String isFlag);

    // 提现取款
    void drawStudentIncome(String studentNo, double incomeMoney);

    // 发放工资
    void giveStudentIncome(String studentNo, double incomeMoney);
}
