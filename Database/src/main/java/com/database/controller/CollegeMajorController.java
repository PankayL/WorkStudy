package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CollegeMajorService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/collegeMajor")
public class CollegeMajorController {
    @PostMapping("/getMajorListByCollege")
    public Result getMajorListByCollege(@RequestBody HashMap map) {
        CollegeMajorService majorService = new CollegeMajorService();
        int collegeNo = (int) map.get("collegeNo");
        return majorService.getMajorListByCollege(collegeNo);
    }
}
