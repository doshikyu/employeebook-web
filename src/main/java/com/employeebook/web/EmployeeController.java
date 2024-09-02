package com.employeebook.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> printAllEmployee() {
        return employeeService.getEmployeeBook();
    }

    @GetMapping(path = "/add")
    public String addEmployeeCtrl(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        /*
       try {
            employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }*/
        employeeService.addEmployee(firstName, lastName);
        return "Сотрудник успешно добавлен";
    }

    @GetMapping(path = "/remove")
    public String removeEmployeeCtrl(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
        //employeeService.removeEmployee(firstName, lastName);
        return "Сотрудник успешно удален";
    }

    @GetMapping(path = "/find")
    public Object findEmployeeCtrl(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

/*    @GetMapping(path = "/find")
    public Object findEmployeeCtrl(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
        return employeeService.findEmployee(firstName, lastName);
    }*/
}
