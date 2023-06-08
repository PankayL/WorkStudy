package com.database.mapper;

import com.database.pojo.CollegeClass;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:57
 */
public interface CollegeClassMapper {
    // 通过专业查询班级
    List<CollegeClass> getClassListByMajor(int majorNo);
}
