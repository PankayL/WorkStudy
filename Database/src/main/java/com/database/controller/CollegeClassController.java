package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CollegeClassService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/collegeClass")
public class CollegeClassController {
    @PostMapping("/getClassListByMajor")
    public Result getClassListByMajor(@RequestBody HashMap map) {
        CollegeClassService stuClassService = new CollegeClassService();
        return stuClassService.getClassListByMajor(map);
    }
}
