package com.employeebook.web;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private final int MAX_EMPLOYEES = 12;

    private List<Employee> employeeBook = new ArrayList<>(Arrays.asList(
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

    public EmployeeService() {
    }

    public List<Employee> getEmployeeBook() {
        return employeeBook;
    }

    public void addEmployee(String firstName, String lastName) {
        if (employeeBook.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employeeBook.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employeeBook.add(employeeToAdd);
    }

    public void removeEmployee(String firstName, String lastName) {
        employeeBook.remove(findEmployee(firstName, lastName));
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employeeBook) {
            if (employee.equals(new Employee(firstName, lastName))) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
}