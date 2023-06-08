package com.database.controller;

import com.database.pojo.Result;
import com.database.service.StudentAssetsService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/studentAssets")
public class StudentAssetsController {
    @PostMapping("/getStudentAssetsById")
    public Result getStudentById(@RequestBody HashMap map) {
        StudentAssetsService studentAssetsService = new StudentAssetsService();
        return studentAssetsService.getStudentAssetsById(map);
    }
}
