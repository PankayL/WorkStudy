package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CollegeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/college")
public class CollegeController {
    @GetMapping("/getCollegeList")
    public Result getCompanyListDeleted() {
        CollegeService collegeService = new CollegeService();
        return collegeService.getCollegeList();
    }
}
