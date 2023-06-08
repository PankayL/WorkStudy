package com.database.Mapper;

import com.database.mapper.CompanyMapper;
import com.database.mapper.CompanyTypeMapper;
import com.database.pojo.Company;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:31
 */
public class CompanyMapperTest {
    @Test
    public void getCompanyById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
        Company companyById = mapper.getCompanyById("12345678");

        System.out.println(companyById);

        // 关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void getCompanyListLimit() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> companyListLimit = mapper.getCompanyListLimit(5, 1, "%%", "%%", "%3%", 0);

        companyListLimit.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }

}
