package com.employeebook.web.controller;

import com.employeebook.web.model.Employee;
import com.employeebook.web.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable int id) {
        return departmentService.getEmployeesByDepartmentId(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public Integer getSumSalaryByDepartment (@PathVariable int id) {
        return departmentService.getSumSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Integer getMaxSalaryInDepartment(@PathVariable int id) {
        return departmentService.getMaxSalaryInDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Integer getMinSalaryInDepartment(@PathVariable int id) {
        return departmentService.getMinSalaryInDepartment(id);
    }
}
