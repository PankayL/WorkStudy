package com.database.service;

import com.database.mapper.PostTypeMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.PostType;
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
public class PostTypeService {
    @Async("taskExecutor")
    public Result getPostTypeList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostTypeMapper mapper = sqlSession.getMapper(PostTypeMapper.class);
        List<PostType> postTypeList = mapper.getPostTypeList();

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(postTypeList, postTypeList.size());
    }
}
