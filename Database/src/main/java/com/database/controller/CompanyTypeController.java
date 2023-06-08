package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CompanyTypeService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-16-10:24
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/type")
public class CompanyTypeController {
    @GetMapping("/getTypeList")
    public Result getStudentListByClass() {
        CompanyTypeService companyTypeService = new CompanyTypeService();
        return companyTypeService.getCompanyTypeList();
    }
}
