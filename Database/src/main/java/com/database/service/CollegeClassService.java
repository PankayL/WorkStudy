package com.database.service;

import com.database.mapper.CollegeClassMapper;
import com.database.pojo.CollegeClass;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class CollegeClassService {
    @Async("taskExecutor")
    public Result getClassListByMajor(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CollegeClassMapper stuClassMapper = sqlSession.getMapper(CollegeClassMapper.class);
        int majorNo = (int) map.get("majorNo");
        List<CollegeClass> classListByMajor = stuClassMapper.getClassListByMajor(majorNo);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(classListByMajor, classListByMajor.size());
    }
}
