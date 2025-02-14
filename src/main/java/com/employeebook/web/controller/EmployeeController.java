package com.employeebook.web.controller;

import com.employeebook.web.model.Employee;
import com.employeebook.web.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<String> findAllEmployees() {
        return employeeService.findAllNames();
    }

    @GetMapping(path = "/add")
    public Employee addEmployeeCtrl(@RequestParam("birthday") String birthday,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("salary") int salary,
                                    @RequestParam("departmentId") int departmentId) {
        return employeeService.addEmployee(birthday, firstName, lastName, salary, departmentId);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployeeCtrl(@RequestParam("birthday") String birthday) {
        return employeeService.removeEmployee(birthday);
    }

    @GetMapping(path = "/find")
    public Employee findEmployeeCtrl(@RequestParam("birthday") String birthday) {
        return employeeService.findEmployee(birthday);
    }
}
