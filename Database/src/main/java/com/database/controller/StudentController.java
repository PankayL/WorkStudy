package com.database.controller;

import com.database.pojo.Result;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {
    @PostMapping("/getStudentListLimit")
    public Result getStudentListByClass(@RequestBody HashMap map) {
        StudentService studentService = new StudentService();
        return studentService.getStudentListByClassOrMajorOrCollege(map);
    }

    @PostMapping("/addStudent")
    public Result addStudent(@RequestBody HashMap map) {
        StudentService studentService = new StudentService();
        return studentService.addStudent(map);
    }

    @PostMapping("/updateStudent")
    public Result updateStudent(@RequestBody HashMap map) {
        StudentService studentService = new StudentService();
        return studentService.updateStudent(map);
    }

    @PostMapping("/login")
    public Result login(@RequestBody HashMap map) {
        StudentService studentService = new StudentService();
        return studentService.login(map);
    }

    @PostMapping("/getStudentById")
    public Result getStudentById(@RequestBody HashMap map) {
        StudentService studentService = new StudentService();
        return studentService.getStudentById(map);
    }

}
