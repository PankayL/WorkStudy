package com.database.service;

import com.database.mapper.ManagerMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.Manager;
import com.database.pojo.Result;
import com.database.pojo.Student;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class ManagerService {
    public Result login(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);

        String no = (String) map.get("no");
        String password = (String) map.get("password");
        int i = mapper.checkManagerNoLogin(no);

        if (i == 1) {
            Manager managerById = mapper.getManagerById(no);
            if (managerById.getManagerPassword().equals(password)) {
                sqlSession.close();
                return Result.suc(managerById);
            }
        }

        // 关闭sqlSession
        sqlSession.close();
        return Result.fail();
    }
}
