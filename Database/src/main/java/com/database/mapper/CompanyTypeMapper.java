package com.database.mapper;

import com.database.pojo.CompanyType;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface CompanyTypeMapper {
    // 获取类型列表
    List<CompanyType> getCompanyTypeList();
}
