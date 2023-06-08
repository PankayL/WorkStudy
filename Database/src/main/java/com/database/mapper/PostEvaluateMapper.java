package com.database.mapper;

import com.database.pojo.PostEvaluate;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface PostEvaluateMapper {
    //获取评论列表
    List<PostEvaluate> getPostEvaluateListByPostNo(int postNo);

    // 添加评论
    int addPostEvaluate(int postNo, String studentNo, String evaluateDetails);
}
