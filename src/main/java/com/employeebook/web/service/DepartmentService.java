package com.employeebook.web.service;

public interface DepartmentService {
    String getMaxSalaryInDepartment(int departmentId);

    String getMinSalaryInDepartment(int departmentId);

    Object getEmployeesByDepartment(Integer departmentId);
}
