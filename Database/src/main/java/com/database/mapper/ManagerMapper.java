package com.database.mapper;

import com.database.pojo.Manager;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface ManagerMapper {
    // 获取管理员信息
    Manager getManagerById(String managerNo);

    // 登录账号检测
    int checkManagerNoLogin(String managerNo);
}
