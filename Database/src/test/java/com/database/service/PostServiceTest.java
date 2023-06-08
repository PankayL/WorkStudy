package com.database.service;

import com.database.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author pankay
 * @create 2022-12-27-17:17
 */
public class PostServiceTest {
    @Test
    public void getPostByIdTest() {
        PostService postService = new PostService();
        Result postById = postService.getPostById(257289004);
    }
}
