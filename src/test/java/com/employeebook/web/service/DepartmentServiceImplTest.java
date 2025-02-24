package com.employeebook.web.service;

import com.employeebook.web.constants.DepartmentConstants;
import com.employeebook.web.exception.EmployeeNotFoundException;
import com.employeebook.web.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void getAllEmployeesByDepartmentTest() {
        when(employeeServiceMock.findAll()).thenReturn(DepartmentConstants.EMPLOYEELIST);

        Map<Integer, List<Employee>> expected = Map.of(
                1, List.of(DepartmentConstants.EMPLOYEE1, DepartmentConstants.EMPLOYEE2),
                2, List.of(DepartmentConstants.EMPLOYEE3),
                3, List.of(DepartmentConstants.EMPLOYEE4)
        );

        assertEquals(expected, out.getAllEmployeesByDepartment());
    }

    @Test
    public void getEmployeesByDepartmentIdTest() {
        when(employeeServiceMock.findAll()).thenReturn(DepartmentConstants.EMPLOYEELIST);

        List<Employee> expected = List.of(DepartmentConstants.EMPLOYEE1, DepartmentConstants.EMPLOYEE2);
        assertEquals(expected, out.getEmployeesByDepartmentId(1));

        expected = List.of(DepartmentConstants.EMPLOYEE3);
        assertEquals(expected, out.getEmployeesByDepartmentId(2));
    }

    @Test
    public void getSumSalaryByDepartmentTest() {
        assertEquals(0, out.getSumSalaryByDepartment(1));

        when(employeeServiceMock.findAll()).thenReturn(DepartmentConstants.EMPLOYEELIST);
        assertEquals(138000, out.getSumSalaryByDepartment(1));
        assertEquals(72000, out.getSumSalaryByDepartment(2));
    }

    @Test
    public void getMaxSalaryInDepartmentTest() {
        when(employeeServiceMock.findAll()).thenReturn(DepartmentConstants.EMPLOYEELIST);

        assertEquals(78000, out.getMaxSalaryInDepartment(1));
        assertEquals(72000, out.getMaxSalaryInDepartment(2));
        assertEquals(250000, out.getMaxSalaryInDepartment(3));
    }

    @Test
    public void getMinSalaryInDepartmentTest() {
        when(employeeServiceMock.findAll()).thenReturn(DepartmentConstants.EMPLOYEELIST);

        assertEquals(60000, out.getMinSalaryInDepartment(1));
        assertEquals(72000, out.getMinSalaryInDepartment(2));
        assertEquals(250000, out.getMinSalaryInDepartment(3));
    }

    @Test
    public void throwExceptionGetMinSalaryNoEmployees() {
        assertThrows(EmployeeNotFoundException.class, () ->  out.getMinSalaryInDepartment(1));
        assertThrows(EmployeeNotFoundException.class, () ->  out.getMinSalaryInDepartment(10));
    }

    @Test
    public void throwExceptionGetMaxSalaryNoEmployees() {
        assertThrows(EmployeeNotFoundException.class, () ->  out.getMaxSalaryInDepartment(1));
        assertThrows(EmployeeNotFoundException.class, () ->  out.getMaxSalaryInDepartment(10));
    }

}
