package com.database.controller;

import com.database.pojo.Result;
import com.database.service.PostEvaluateService;
import com.database.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/postEvaluate")
public class PostEvaluateController {
    // 获取评论列表
    @PostMapping("/getPostEvaluateListByPostNo")
    public Result getPostEvaluateListByPostNo(@RequestBody HashMap map) {
        PostEvaluateService postEvaluateService = new PostEvaluateService();
        return postEvaluateService.getPostEvaluateListByPostNo(map);
    }

    // 获取评论列表
    @PostMapping("/addPostEvaluate")
    public Result addPostEvaluate(@RequestBody HashMap map) {
        PostEvaluateService postEvaluateService = new PostEvaluateService();
        return postEvaluateService.addPostEvaluate(map);
    }
}
