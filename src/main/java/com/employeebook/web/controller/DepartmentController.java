package com.employeebook.web.controller;

import com.employeebook.web.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public String getMaxSalaryInDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.getMaxSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public String getMinSalaryInDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.getMinSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Object getEmployees(@RequestParam(required = false) Integer departmentId) {
            return departmentService.getEmployeesByDepartment(departmentId);
    }
}
