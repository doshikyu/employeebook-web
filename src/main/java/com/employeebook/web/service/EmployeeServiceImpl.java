package com.employeebook.web.service;

import com.employeebook.web.exception.EmployeeAlreadyAddedException;
import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.exception.EmployeeStorageIsFullException;
import com.employeebook.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_EMPLOYEES = 12;
    private final Map<String, Employee> employeesMap = new HashMap<>();

    public EmployeeServiceImpl() {
        employeesMap.putAll(Map.of(
                "741223", new Employee("Анна", "Каренина"),
                "760428", new Employee("Дмитрий", "Гуров"),
                "830109", new Employee("Иван", "Чимша-Гималайский"),
                "670315", new Employee("Дмитрий", "Старцев"),
                "850726", new Employee("Сергей", "Толстой"),
                "900518", new Employee("Михаил", "Тонкий"),
                "080254", new Employee("Иван", "Лаевский"),
                "170699", new Employee("Надежда", "Михайлова"),
                "700727", new Employee("Андрей", "Рагин"),
                "921231", new Employee("Николай", "Подгорин")
        ));
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employeesMap.values());
    }

    @Override
    public Employee addEmployee(String birthday, String firstName, String lastName) {
        if (employeesMap.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        Employee employeeToAdd = new Employee(firstName, lastName);
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
}

