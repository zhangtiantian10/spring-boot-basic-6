package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {
  private List<Employee> employees = new ArrayList<>();

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

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity getEmployee(@PathVariable int id) {

    Optional optional = employees.stream()
        .filter(employee -> employee.getId() == id)
        .findFirst();

    if (!optional.isPresent()) {
      return new ResponseEntity<>("该用户不存在", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optional.get(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteEmployee(@PathVariable int id) {

    employees = employees.stream()
        .filter(employee -> employee.getId() != id)
        .collect(Collectors.toList());

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody Employee body) {

    employees = employees.stream()
        .map(employee -> {
          if (employee.getId() == id) {
            employee.setName(body.getName());
            employee.setAge(body.getAge());
            employee.setGender(body.getGender());
          }

          return employee;
        })
        .collect(Collectors.toList());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
