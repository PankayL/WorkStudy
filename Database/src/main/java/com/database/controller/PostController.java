package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CompanyService;
import com.database.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/post")
public class PostController {
    @PostMapping("/getPostListLimit")
    public Result getPostListLimit(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.getPostListLimit(map);
    }

    @PostMapping("/deletePost")
    public Result deletePost(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.deletePost((int)map.get("postNo"));
    }

    @PostMapping("/checkPost")
    public Result checkPost(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.checkPost(map);
    }

    @PostMapping("/getPostById")
    public Result getPostById(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.getPostById((int) map.get("no"));
    }

    @PostMapping("/getPostTotalByCompanyNo")
    public Result getPostTotalByCompanyNo(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.getPostTotalByCompanyNo(map);
    }

    @PostMapping("/getPostListLimitByCompanyNo")
    public Result getPostListLimitByCompanyNo(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.getPostListLimitByCompanyNo(map);
    }

    @PostMapping("/updateCompanyPostStateByPostNo")
    public Result updateCompanyPostStateByPostNo(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.updateCompanyPostStateByPostNo(map);
    }

    @PostMapping("/getPostListLimitByCompanyNoDesc")
    public Result getPostListLimitByCompanyNoDesc(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.getPostListLimitByCompanyNoDesc(map);
    }

    @PostMapping("/addPost")
    public Result addPost(@RequestBody HashMap map) {
        PostService postService = new PostService();
        return postService.addPost(map);
    }
}
