package com.employeebook.web.constants;

import com.employeebook.web.model.Employee;

import java.util.List;
import java.util.Map;

public class DepartmentConstants {
    public static final String FIRSTNAME = "Sergey";
    public static final String LASTNAME = "Tolstoy";
    public static final int SALARY = 78000;
    public static final int DEPARTMENT = 1;


    public static final Employee EMPLOYEE1 = new Employee (FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
    public static final Employee EMPLOYEE2 = new Employee ("Michael", "Tonki", 60000, 1);
    public static final Employee EMPLOYEE3 = new Employee ("Ivan", "Laevski", 72000, 2);
    public static final Employee EMPLOYEE4 = new Employee ("Nadejda", "Michaelovna", 250000, 3);

    public static final Map<String, Employee> EMPLOYEE_MAP = Map.of(
            "850726", EMPLOYEE1,
            "900518", EMPLOYEE2,
            "080254", EMPLOYEE3,
            "170699", EMPLOYEE4
    );


    public static final List<Employee> EMPLOYEELIST = List.of(
            EMPLOYEE1,
            EMPLOYEE2,
            EMPLOYEE3,
            EMPLOYEE4
    );
}
