package com.database.controller;

import com.database.pojo.Result;
import com.database.service.ManagerService;
import com.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/manager")
public class ManagerController {
    @PostMapping("/login")
    public Result login(@RequestBody HashMap map) {
        ManagerService managerService = new ManagerService();
        return managerService.login(map);
    }
}
