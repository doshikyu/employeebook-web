package com.employeebook.web.service;

import com.employeebook.web.exception.EmployeeAlreadyAddedException;
import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.exception.EmployeeStorageIsFullException;
import com.employeebook.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_EMPLOYEES = 12;
    private final List<Employee> employeeBook;

    public EmployeeServiceImpl() {
        employeeBook = new ArrayList<>(Arrays.asList(
            new Employee("Анна", "Каренина"),
            new Employee("Дмитрий", "Гуров"),
            new Employee("Иван", "Чимша-Гималайский"),
            new Employee("Дмитрий", "Старцев"),
            new Employee("Сергей", "Толстой"),
            new Employee("Михаил", "Тонкий"),
            new Employee("Иван", "Лаевский"),
            new Employee("Надежда", "Михайлова"),
            new Employee("Андрей", "Рагин"),
            new Employee("Николай", "Подгорин")));
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeBook;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employeeBook.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employeeBook.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employeeBook.add(employeeToAdd);
        return employeeToAdd;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = findEmployee(firstName, lastName);
        employeeBook.remove(employeeToRemove);
        return employeeToRemove;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employeeBook) {
            if (employee.equals(new Employee(firstName, lastName))) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
}