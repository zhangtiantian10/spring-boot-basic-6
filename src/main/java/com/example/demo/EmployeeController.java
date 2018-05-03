package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {
  @Autowired
  private List<Employee> employees;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity addEmployee(@RequestBody Employee employee) {
    employee.setId(employees.size());
    employees.add(employee);

    return new ResponseEntity(HttpStatus.CREATED);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity getAllEmployees() {

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }
}
