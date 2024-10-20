package com.mayo.crud.service;
import com.mayo.crud.exception.ApiException;
import com.mayo.crud.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(long id) throws ApiException;
    Employee updateEmployee(Employee employee,long id) throws ApiException;
    void deleteEmployee(long id) throws ApiException;
}
