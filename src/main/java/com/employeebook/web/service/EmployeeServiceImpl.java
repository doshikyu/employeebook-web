package com.employeebook.web.service;

import com.employeebook.web.exception.EmployeeAlreadyAddedException;
import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.exception.EmployeeStorageIsFullException;
import com.employeebook.web.exception.InvalidInputException;
import com.employeebook.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_EMPLOYEES = 12;
    private final Map<String, Employee> employeesMap;

    public EmployeeServiceImpl() {
        this.employeesMap = new HashMap<>();
        // testing purposes
        employeesMap.putAll(Map.of(
                "741223", new Employee("Анна", "Каренина", 80000, 1),
                "760428", new Employee("Дмитрий", "Гуров", 190000, 2),
                "830109", new Employee("Иван", "Чимша-Гималайский", 70000, 3),
                "670315", new Employee("Дмитрий", "Старцев", 150000, 4),
                "850726", new Employee("Сергей", "Толстой", 78000, 1),
                "900518", new Employee("Михаил", "Тонкий", 60000, 2),
                "080254", new Employee("Иван", "Лаевский", 72000, 3),
                "170699", new Employee("Надежда", "Михайлова", 250000, 4),
                "700727", new Employee("Андрей", "Рагин", 210000, 2),
                "921231", new Employee("Николай", "Подгорин", 90000, 4)
        ));
    }

    @Override
    public List<String> findAllNames() {
        List<Employee> employees = employeesMap.values().stream().toList();
        return employees.stream()
                .map(employee -> employee.getFullName())
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employeesMap.values());
    }

    @Override
    public Employee addEmployee(String birthday, String firstName, String lastName, int salary, int department) {
        if (employeesMap.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        Set<Integer> validDepartments = Set.of(1, 2, 3, 4);
        if (!validDepartments.contains(department)) {
            throw new InvalidInputException("No Department");
        }

        validateInput(firstName, lastName);

        Employee employeeToAdd = new Employee(firstName, lastName, salary, department);
        if (employeesMap.containsKey(birthday) && employeesMap.get(birthday).getLastName().equals(lastName)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employeesMap.put(birthday, employeeToAdd);
        return employeeToAdd;
    }

    @Override
    public Employee removeEmployee(String birthday) {
        Employee employeeToRemove = findEmployee(birthday);
        employeesMap.remove(birthday);
        return employeeToRemove;
    }

    @Override
    public Employee findEmployee(String birthday) {
        if (employeesMap.containsKey(birthday)) return employeesMap.get(birthday);
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) & isAlpha(lastName))) {
            throw new InvalidInputException("Invalid first or last name input.");
        }
    }
}

