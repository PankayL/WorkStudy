package com.database.controller;

import com.database.pojo.Result;
import com.database.service.StudentIncomeService;
import com.database.service.StudentResumeService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/studentResume")
public class StudentResumeController {
    @PostMapping("/addStudentResume")
    public Result addStudentResume(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.addStudentResume(map);
    }

    @PostMapping("/getStudentResumeByStudentNo")
    public Result getStudentResumeListLimitByStudentNo(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.getStudentResumeListLimitByStudentNo(map);
    }

    @PostMapping("/getStudentResumeListByStudentNo")
    public Result getStudentResumeAllListLimitByStudentNo(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.getStudentResumeAllListLimitByStudentNo(map);
    }

    @PostMapping("/deleteStudentResume")
    public Result deleteStudentResume(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.deleteStudentResume(map);
    }

    @PostMapping("/leaveStudentResume")
    public Result leaveStudentResume(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.leaveStudentResume(map);
    }

    @PostMapping("/getStudentResumeListLimitByCompanyNo")
    public Result getStudentResumeListLimitByCompanyNo(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.getStudentResumeListLimitByCompanyNo(map);
    }

    @PostMapping("/updateStudentResumeState")
    public Result updateStudentResumeState(@RequestBody HashMap map) {
        StudentResumeService studentResumeService = new StudentResumeService();
        return studentResumeService.updateStudentResumeState(map);
    }
}
