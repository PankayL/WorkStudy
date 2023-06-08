package com.database.service;

import com.database.mapper.PostMapper;
import com.database.pojo.Post;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@Data
@Component
public class PostThreadService {

    @Async("taskExecutor")
    public void setCount(int postNo, int postCount) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        Thread t = Thread.currentThread();
        System.out.println(" 2 " + t.getName());

        while(mapper.setPostCount(postNo, postCount) == 0) {
            Post postById = mapper.getPostById(postNo);
            postCount = postById.getPostCount();
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
