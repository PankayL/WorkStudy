package com.database.Mapper;

import com.database.mapper.PostMapper;
import com.database.pojo.Company;
import com.database.pojo.Post;
import com.database.pojo.Result;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:31
 */
public class PostMapperTest {
    @Test
    public void getPostByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        Post postById = mapper.getPostById(257289004);
        System.out.println(postById);

        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getPostListLimitTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);


        List<Post> postListLimit = mapper.getPostListLimit(5, 1, "%%", "%%", 0, 0);
        postListLimit.forEach(System.out::println);
        // 关闭sqlSession
        sqlSession.close();
    }
}
