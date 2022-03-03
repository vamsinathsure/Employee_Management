package com.employeesdata.employees.controller;

import javax.validation.*;
import com.employeesdata.employees.service.EmployeeService;

import com.employeesdata.employees.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.*;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class EmpController {

    @Autowired
    private EmployeeService empService;


    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> getEmployees() throws Exception{
        List<Employees> listOfAll = empService.getEmp();
        return new ResponseEntity<List<Employees>>(listOfAll, HttpStatus.OK);

    }
    //get employee by id
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        Employees empg = empService.getEmployee(id);
        return new ResponseEntity<Employees>(empg,HttpStatus.OK);
    }


    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employees empa) throws Exception{
        Employees empsave = empService.saveEmployee(empa);
        return new ResponseEntity<Employees>(empsave,HttpStatus.CREATED);

    }

    //delete employee
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        empService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }










}
