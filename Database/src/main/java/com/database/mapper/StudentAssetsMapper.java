package com.database.mapper;

import com.database.pojo.StudentAssets;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface StudentAssetsMapper {
    // 获取学生资产
    StudentAssets getStudentAssetsById(String studentNo);

    // 更新学生资产
    Integer updateStudentAssets(String studentNo, double studentMoney, int version);
}
