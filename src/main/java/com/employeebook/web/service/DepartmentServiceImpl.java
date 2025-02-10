package com.employeebook.web.service;

import com.employeebook.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employees = new EmployeeServiceImpl();
    private Map<Integer, String> departmentService;

    public DepartmentServiceImpl() {
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
    public String getMaxSalaryInDepartment(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(employee -> employee.toString())
                .orElseThrow(() -> new RuntimeException("No Department or Employee Found"));
    }

    @Override
    public String getMinSalaryInDepartment(int departmentId) {
        return employees.findAll()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(employee -> employee.toString())
                .orElseThrow(() -> new RuntimeException("No Department or Employee Found"));
    }

    @Override
    public Object getEmployeesByDepartment(Integer departmentId) {
        if (departmentId == null) {
            return employees.findAll().stream()
                    .collect(Collectors.groupingBy(e -> e.getDepartmentId()))
                    .toString();
        }
        return employees.findAll()
                .stream()
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .map(employee -> employee.getFullName())
                .toList();
    }
}
