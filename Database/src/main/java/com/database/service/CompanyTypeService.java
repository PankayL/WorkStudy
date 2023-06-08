package com.database.service;

import com.database.mapper.CompanyTypeMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.CompanyType;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-16-10:28
 */
public class CompanyTypeService {
    @Async("taskExecutor")
    public Result getCompanyTypeList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyTypeMapper mapper = sqlSession.getMapper(CompanyTypeMapper.class);

        List<CompanyType> companyTypeList = mapper.getCompanyTypeList();

        // 关闭sqlSession
        sqlSession.close();
        if (companyTypeList.size() >= 1) {
            return Result.suc(companyTypeList, companyTypeList.size());
        }
        return Result.fail();
    }
}
