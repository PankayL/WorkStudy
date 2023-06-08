package com.database.controller;

import com.database.pojo.Result;
import com.database.service.StudentIncomeService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/studentIncome")
public class StudentIncomeController {
    @PostMapping("/getStudentIncomeListLimit")
    public Result getStudentIncomeListLimit(@RequestBody HashMap map) {
        StudentIncomeService studentIncomeService = new StudentIncomeService();
        return studentIncomeService.getStudentIncomeListList(map);
    }

    @PostMapping("/drawStudentIncome")
    public Result drawStudentIncome(@RequestBody HashMap map) {
        StudentIncomeService studentIncomeService = new StudentIncomeService();
        return studentIncomeService.drawStudentIncome(map);
    }

    @PostMapping("/giveStudentIncome")
    public Result giveStudentIncome(@RequestBody HashMap map) {
        StudentIncomeService studentIncomeService = new StudentIncomeService();
        return studentIncomeService.giveStudentIncome(map);
    }
}
