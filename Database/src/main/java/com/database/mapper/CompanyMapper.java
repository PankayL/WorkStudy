package com.database.mapper;

import com.database.pojo.Company;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface CompanyMapper {
    // 通过企业号获取企业信息
    Company getCompanyById(String companyNo);

    //分页查询
    List<Company> getCompanyListLimit(int pageSize, int pageNum, String companyName, String peopleName, String typeNo, int deleted);

    // 获取总数total
    int getCompanyTotal(String companyName, String peopleName, String typeNo, int deleted);

    // 添加单位
    // 检测账号是否已经存在
    int checkCompanyNo(String companyNo);
    void addCompany(String companyName, String companyNo, String companyPassword, String companyPeople, int companyType);
    // 登录检测
    int checkCompanyNoLogin(String companyNo);

    // 删除单位
    void deleteCompany(String companyNo);

    // 修改单位信息
    void updateCompany(int type, String companyNo, String companyName, String peopleName, String companyPassword, int companyType);
}
