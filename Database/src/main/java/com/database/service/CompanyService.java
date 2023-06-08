package com.database.service;

import com.database.mapper.CompanyMapper;
import com.database.mapper.CompanyTypeMapper;
import com.database.pojo.Company;
import com.database.pojo.CompanyType;
import com.database.pojo.Result;
import com.database.pojo.Student;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class CompanyService {
    @Async("taskExecutor")
    public Result getCompanyInfor(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);

        String companyNo = (String) hashMap.get("companyNo");
        Company companyById = mapper.getCompanyById(companyNo);

        // 关闭sqlSession
        sqlSession.close();
        if (companyById != null && !("".equals(companyNo))) {
            return Result.suc(companyById);
        }
        return Result.fail();
    }

    @Async("taskExecutor")
    public Result getCompanyListLimit(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
        int pageNum = (int) hashMap.get("pageNum");
        int pageSize = (int) hashMap.get("pageSize");
        int deleted = (int) hashMap.get("deleted");
        String companyName =  "%" + (String) hashMap.get("companyName") + "%";
        String peopleName = "%" + (String) hashMap.get("peopleName") + "%";
        String type;
        if (!"".equals(hashMap.get("type"))) {
            type = "%" +  (int) hashMap.get("type") + "%";
        } else {
            type = "%" + "" + "%";
        }

        System.out.println(type);

        List<Company> companyListLimit = mapper.getCompanyListLimit(pageSize, pageNum, companyName, peopleName, type, deleted);
        int companyTotal = mapper.getCompanyTotal(companyName, peopleName, type, deleted);
        // 关闭sqlSession
        sqlSession.close();
        if (companyListLimit.size() >= 1) {
            return Result.suc(companyListLimit, companyTotal);
        }
        return Result.fail();
    }

    @Async("taskExecutor")
    public Result addCompany(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
        int result = mapper.checkCompanyNo((String) hashMap.get("companyNo"));
        if (result == 0) {
            String companyName = (String) hashMap.get("companyName");
            String companyNo = (String) hashMap.get("companyNo");
            String companyPassword = (String) hashMap.get("companyPassword");
            String companyPeople = (String) hashMap.get("companyPeople");
            int companyType = (int)hashMap.get("companyType");
            mapper.addCompany(companyName,companyNo,companyPassword,companyPeople,companyType);
            sqlSession.commit();
            sqlSession.close();
            return Result.suc();
        }
        // 关闭sqlSession
        sqlSession.close();
        return Result.fail();
    }

    @Async("taskExecutor")
    public Result deleteCompany(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
        mapper.deleteCompany((String) hashMap.get("companyNo"));
        sqlSession.commit();

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc();
    }

    @Async("taskExecutor")
    public Result updateCompany(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);

        String companyNo = (String) hashMap.get("companyNo");
        String companyName = (String) hashMap.get("companyName");
        String peopleName = (String) hashMap.get("peopleName");
        int type = 0;
        int companyType = 0;
        String companyPassword = "";
        if ("".equals(hashMap.get("companyPassword"))) {
            if ("".equals(hashMap.get("type"))) {
                type = 1;
            } else {
                companyType = (int) hashMap.get("type");
                type = 3;
            }
        } else {
            companyPassword = (String) hashMap.get("companyPassword");
            if ("".equals(hashMap.get("type"))) {
                type = 2;
            } else {
                companyType = (int) hashMap.get("type");
                type = 4;
            }
        }

        mapper.updateCompany(type, companyNo, companyName, peopleName, companyPassword, companyType);
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();

        return Result.suc();

    }

    @Async("taskExecutor")
    public Result Login(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);

        String no = (String) hashMap.get("no");
        String password = (String) hashMap.get("password");
        int i = mapper.checkCompanyNoLogin(no);

        if (i == 1) {
            Company companyById = mapper.getCompanyById(no);
            if (companyById.getCompanyPassword().equals(password)) {
                sqlSession.close();
                return Result.suc(companyById);
            }
        }

        // 关闭sqlSession
        sqlSession.close();
        return Result.fail();
    }
}
