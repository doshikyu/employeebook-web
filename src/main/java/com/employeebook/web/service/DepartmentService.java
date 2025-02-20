package com.employeebook.web.service;

import com.employeebook.web.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<Integer, List<Employee>> getAllEmployeesByDepartment();

    List<Employee> getEmployeesByDepartmentId(int departmentId);

    Integer getSumSalaryByDepartment(int departmentId);

    Integer getMaxSalaryInDepartment(int departmentId);

    Integer getMinSalaryInDepartment(int departmentId);
}
