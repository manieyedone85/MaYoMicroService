package com.mayo.crud.controller;

import com.mayo.crud.exception.ApiException;
import com.mayo.crud.exception.ExceptionModel;
import com.mayo.crud.model.Employee;
import com.mayo.crud.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Management", description = "Operations related to employee management")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    @Operation(summary = "Save Employee",
            description = "Save employee information to database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class)))
    })
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //GetAll Rest Api
    @Operation(summary = "Get all employees",
            description = "To Retrieve all employees information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class)))
    })
    @GetMapping()
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    //Get by Id Rest Api
    @Operation(summary = "Get an employee by ID",
            description = "Retrieves a specific employee based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@Parameter(description = "ID of the employee to be retrieved", required = true)
                                                    @PathVariable("id") long employeeID) throws ApiException {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeID), HttpStatus.OK);
    }

    //Update Rest Api
    @Operation(summary = "Update employee by ID",
            description = "Update a specific employee based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class)))
    })
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@Parameter(description = "ID of the employee to be update", required = true)
                                                   @PathVariable("id") long id,
                                                   @RequestBody Employee employee) throws ApiException {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //Delete Rest Api
    @Operation(summary = "Delete an employee by ID",
            description = "Delete a specific employee based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionModel.class)))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@Parameter(description = "ID of the employee to be delete", required = true)
                                                 @PathVariable("id") long id) throws ApiException {
        //delete employee from db
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted Successfully.", HttpStatus.OK);
    }

}
