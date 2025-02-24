package com.employeebook.web.service;

import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employees;
    private Map<Integer, String> departmentService;

    public DepartmentServiceImpl(EmployeeService employees) {
        this.employees = employees;
        departmentService = new HashMap<>();
        departmentService.put(1, "Маркетинг");
        departmentService.put(2, "IT");
        departmentService.put(3, "HR");
        departmentService.put(4, "Финансы");
    }

    public Integer getDepartmentId(String departmentName) {
        return departmentService.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(departmentName))
                .map(e -> e.getKey())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Department " + departmentName + " does not exist"));
    }

    public String getDepartmentName(Integer dptId) {
        return departmentService.get(dptId);
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employees.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartmentId()));
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getSumSalaryByDepartment(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Integer getMaxSalaryInDepartment(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(employee -> employee.getSalary())
                .orElseThrow(() -> new EmployeeNotFoundException("No Department or Employee Found"));
    }

    @Override
    public Integer getMinSalaryInDepartment(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(employee -> employee.getSalary())
                .orElseThrow(() -> new EmployeeNotFoundException("No Department or Employee Found"));
    }
}