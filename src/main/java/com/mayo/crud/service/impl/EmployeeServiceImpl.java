package com.mayo.crud.service.impl;

import com.mayo.crud.exception.ApiException;
import com.mayo.crud.model.Employee;
import com.mayo.crud.repository.EmployeeRepository;
import com.mayo.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepsitory;

    //save employee in database
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepsitory.save(employee);
    }

    //get all employee form database
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepsitory.findAll();
    }

    //get employee using id
    @Override
    public Employee getEmployeeById(long id) throws ApiException {
        Optional<Employee> employee = employeeRepsitory.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw ApiException.builder()
                    .title("Warning!")
                    .message(String.format("This ID %s is not found.", id))
                    .code(HttpStatus.BAD_REQUEST.value())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    //update employee
    @Override
    public Employee updateEmployee(Employee employee, long id) throws ApiException {
        Employee existingEmployee = employeeRepsitory.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .title("Warning!")
                        .message(String.format("This ID %s is not found.", id))
                        .code(HttpStatus.BAD_REQUEST.value())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
        );
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save
        employeeRepsitory.save(existingEmployee);
        return existingEmployee;
    }

    //delete employee
    @Override
    public void deleteEmployee(long id) throws ApiException {
        //check
        employeeRepsitory.findById(id).orElseThrow(() -> ApiException.builder()
                .title("Warning!")
                .message(String.format("This ID %s is not found.", id))
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build());
        //delete
        employeeRepsitory.deleteById(id);
    }
}
