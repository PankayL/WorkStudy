package com.database.service;

import com.database.mapper.CollegeMapper;
import com.database.pojo.College;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class CollegeService {
    @Async("taskExecutor")
    public Result getCollegeList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CollegeMapper collegeMapper = sqlSession.getMapper(CollegeMapper.class);
        List<College> collegeList = collegeMapper.getCollegeList();

        // 提交事务
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(collegeList, collegeList.size());
    }
}
