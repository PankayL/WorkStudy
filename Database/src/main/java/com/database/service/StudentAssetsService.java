package com.database.service;

import com.database.mapper.StudentAssetsMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.Result;
import com.database.pojo.Student;
import com.database.pojo.StudentAssets;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class StudentAssetsService {
    public Result getStudentAssetsById(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentAssetsMapper mapper = sqlSession.getMapper(StudentAssetsMapper.class);

        String no = (String) map.get("no");
        StudentAssets studentAssetsById = mapper.getStudentAssetsById(no);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(studentAssetsById);
    }
}
