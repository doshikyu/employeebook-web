package com.employeebook.web.service;

import com.employeebook.web.exception.EmployeeAlreadyAddedException;
import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.exception.EmployeeStorageIsFullException;
import com.employeebook.web.exception.InvalidInputException;
import com.employeebook.web.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.employeebook.web.constants.DepartmentConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {
    private EmployeeServiceImpl out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    public void shouldAddNewEmployee() {
        Employee employee = out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        assertNotNull(employee);
        assertEquals("Sergey Tolstoy", employee.getFullName());
        assertEquals(78000, employee.getSalary());
        assertEquals(1, employee.getDepartmentId());
        assertEquals(1, out.findAll().size());
    }

    @Test
    public void shouldFindEmployee() {
        out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        Employee foundEmployee = out.findEmployee("850726");
        assertNotNull(foundEmployee);
        assertEquals("Sergey Tolstoy", foundEmployee.getFullName());
        assertEquals(78000, foundEmployee.getSalary());
        assertEquals(1, foundEmployee.getDepartmentId());
    }

    @Test
    public void shouldRemoveEmployee() {
        out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        Employee removedEmployee = out.removeEmployee("850726");
        assertNotNull(removedEmployee);
        assertEquals("Sergey Tolstoy", removedEmployee.getFullName());
        assertEquals(78000, removedEmployee.getSalary());
        assertEquals(1, removedEmployee.getDepartmentId());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee("850726"));
    }

    @Test
    public void shouldReturnNamesOfAllEmployees() {
        out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        out.addEmployee("880808", "Anna", "Karenina", 200000, 2);
        List<String> actual = out.findAllNames();
        assertNotNull(actual);
        assertTrue(actual.contains("Sergey Tolstoy"));
        assertTrue(actual.contains("Anna Karenina"));

        List<String> expected = List.of("Sergey Tolstoy", "Anna Karenina");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyNamesOfAllEmployees() {
        assertTrue(out.findAllNames().isEmpty());
    }

    @Test
    public void shouldReturnListOfEmployees() {
        out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        out.addEmployee("880808", "Anna", "Karenina", 200000, 2);
        List<Employee> actual = out.findAll();
        assertNotNull(actual);
        assertEquals(2, out.findAll().size());
    }

    @Test
    public void shouldReturnEmptyListOfEmployees() {
        assertTrue(out.findAll().isEmpty());
    }


    /*
    *
    Exceptions
    *
    */

    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee("850726", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT));
    }

    @Test
    public void shouldThrowEmployeeStorageIsFullException() {
        for (int i = 0; i < 12; i++) {
            out.addEmployee("850726" + (i + 1), FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> out.addEmployee("700808", FIRSTNAME, LASTNAME, SALARY, DEPARTMENT));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionFindEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee("000000"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionRemoveEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee("000000"));
    }

    @Test
    public void shouldThrowInvalidInputExceptionName() {
        assertThrows(InvalidInputException.class, () -> out.addEmployee("00", "ragu123", "Ratatui", 80, 1));
    }
}
