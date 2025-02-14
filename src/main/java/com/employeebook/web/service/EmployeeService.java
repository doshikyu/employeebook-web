package com.employeebook.web.service;

import com.employeebook.web.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String birthday, String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String birthday);

    Employee findEmployee(String birthday);

    List<String> findAllNames();

    List<Employee> findAll();

}
