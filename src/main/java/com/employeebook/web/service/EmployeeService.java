package com.employeebook.web.service;

import com.employeebook.web.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String birthday, String firstName, String lastName);

    Employee removeEmployee(String birthday);

    Employee findEmployee(String birthday);

    Collection<Employee> findAll();

}
