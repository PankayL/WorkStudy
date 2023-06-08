package com.database.mapper;

import com.database.pojo.CollegeMajor;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:57
 */
public interface CollegeMajorMapper {
    // 根据学院获取专业
    List<CollegeMajor> getMajorListByCollege(int collegeNo);
}
