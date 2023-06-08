package com.database.mapper;

import com.database.pojo.College;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:56
 */
public interface CollegeMapper {
    // 获取所有学院
    List<College> getCollegeList();
}
