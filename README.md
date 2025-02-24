# Spring Boot Employee Book

A simple Spring Boot application to manage employee data, allowing for the management of employees by department, salary calculations, and employee-related operations like adding, removing, and finding employees.

## Features:
- Add a new employee
- Remove an existing employee
- Search for an employee by ID or department
- View all employees or employees grouped by department
- Calculate department-based salary statistics (sum, max, min)

## Endpoints:

| Method | Endpoint                         | Description                                    |
|--------|----------------------------------|------------------------------------------------|
| GET    | `/departments/employees`        | Get all employees grouped by department        |
| GET    | `/departments/{id}/employees`   | Get employees by specific department ID        |
| GET    | `/departments/{id}/salary/sum`  | Get the sum of salaries in a department        |
| GET    | `/departments/{id}/salary/max`  | Get the maximum salary in a department         |
| GET    | `/departments/{id}/salary/min`  | Get the minimum salary in a department         |
| GET    | `/add`                           | Add a new employee                             |
| GET    | `/remove`                        | Remove an employee                             |
| GET    | `/find`                          | Find an employee by ID or department           |
| GET    | `/`                              | View all employees                             |

## Technologies:
- Java
- Spring Boot
- Spring MVC
- Java Streams (for salary statistics and grouping)

## Unit Tests:
This project includes unit tests for the core services, ensuring robust functionality and reliability. The tests are written using JUnit and Mockito.

### DepartmentService Tests:
- **getAllEmployeesByDepartmentTest**: Verifies that employees are grouped by department.
- **getEmployeesByDepartmentIdTest**: Ensures correct employee retrieval by department ID.
- **getSumSalaryByDepartmentTest**: Validates salary sum calculations for each department.
- **getMaxSalaryInDepartmentTest**: Confirms the correct maximum salary retrieval by department.
- **getMinSalaryInDepartmentTest**: Tests the retrieval of the minimum salary by department.
- **Exception Tests**: Ensures appropriate exceptions (EmployeeNotFoundException) are thrown when necessary.

### EmployeeService Tests:
- **shouldAddNewEmployee**: Verifies that new employees are added correctly.
- **shouldFindEmployee**: Confirms employee retrieval by ID.
- **shouldRemoveEmployee**: Ensures employees can be removed.
- **shouldReturnNamesOfAllEmployees**: Validates the retrieval of all employee names.
- **shouldThrowEmployeeAlreadyAddedException**: Tests that an exception is thrown when attempting to add a duplicate employee.
- **shouldThrowEmployeeStorageIsFullException**: Verifies that the storage limit is enforced.
- **shouldThrowEmployeeNotFoundExceptionFindEmployee**: Tests the exception when an employee is not found.
- **shouldThrowInvalidInputExceptionName**: Ensures input validation is enforced on employee data.

## Installation:

1. Clone the repository:
   ```bash
   git clone https://github.com/doshikyu/employeebook
