package com.database.service;

import com.database.mapper.PostEvaluateMapper;
import com.database.mapper.PostMapper;
import com.database.pojo.Post;
import com.database.pojo.PostEvaluate;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class PostEvaluateService {
    // 获取评论列表
    public Result getPostEvaluateListByPostNo(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostEvaluateMapper mapper = sqlSession.getMapper(PostEvaluateMapper.class);

        int postNo = (int) hashMap.get("postNo");
        List<PostEvaluate> postEvaluateListByPostNo = mapper.getPostEvaluateListByPostNo(postNo);

        sqlSession.close();
        return Result.suc(postEvaluateListByPostNo, postEvaluateListByPostNo.size());
    }

    // 添加评论
    public Result addPostEvaluate(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostEvaluateMapper mapper = sqlSession.getMapper(PostEvaluateMapper.class);

        int postNo = (int) hashMap.get("postNo");
        String studentNo = (String) hashMap.get("studentNo");
        String evaluateDetails = (String) hashMap.get("evaluateDetails");
        int i = 0;
        if (!("".equals(hashMap.get("evaluateDetails")) || null == hashMap.get("evaluateDetails"))) {
            i = mapper.addPostEvaluate(postNo, studentNo, evaluateDetails);
            sqlSession.commit();
        }

        sqlSession.close();
        if (i == 1) {
            return Result.suc();
        }
        return Result.fail();
    }
}
