package com.database.Mapper;

import com.database.mapper.PostTypeMapper;
import com.database.pojo.PostType;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:31
 */
public class PostTypeMapperTest {
    @Test
    public void getPostTypeListTest() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        PostTypeMapper mapper = sqlSession.getMapper(PostTypeMapper.class);
        List<PostType> postTypeList = mapper.getPostTypeList();
        postTypeList.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }
}
