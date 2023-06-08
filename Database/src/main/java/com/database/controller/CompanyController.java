package com.database.controller;

import com.database.pojo.Result;
import com.database.service.CompanyService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/company")
public class CompanyController {
    @PostMapping("/getCompanyListLimit")
    public Result getCompanyListLimit(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.getCompanyListLimit(map);
    }

    @PostMapping("/addCompany")
    public Result addCompany(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.addCompany(map);
    }

    @PostMapping("/deleteCompany")
    public Result deleteCompany(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.deleteCompany(map);
    }

    @PostMapping("/updateCompany")
    public Result updateCompany(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.updateCompany(map);
    }

    @PostMapping("/login")
    public Result login(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.Login(map);
    }

    @PostMapping("/getCompanyInfor")
    public Result getCompanyInfor(@RequestBody HashMap map) {
        CompanyService companyService = new CompanyService();
        return companyService.getCompanyInfor(map);
    }
}
