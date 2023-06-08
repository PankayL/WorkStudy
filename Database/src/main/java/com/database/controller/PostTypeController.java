package com.database.controller;

import com.database.pojo.Result;
import com.database.service.PostTypeService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/postType")
public class PostTypeController {
    @GetMapping("/getPostTypeList")
    public Result getStudentListByClass() {
        PostTypeService postTypeService = new PostTypeService();
        return postTypeService.getPostTypeList();
    }

}
