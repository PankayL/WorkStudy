package com.database.service;

import com.database.mapper.CollegeMajorMapper;
import com.database.pojo.CollegeMajor;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class CollegeMajorService {
    @Async("taskExecutor")
    public Result getMajorListByCollege(int collegeNo) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CollegeMajorMapper majorMapper = sqlSession.getMapper(CollegeMajorMapper.class);
        List<CollegeMajor> majorListByCollege = majorMapper.getMajorListByCollege(collegeNo);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(majorListByCollege, majorListByCollege.size());
    }
}
