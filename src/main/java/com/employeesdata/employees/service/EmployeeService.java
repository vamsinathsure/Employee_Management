package com.employeesdata.employees.service;

import antlr.Utils;
import com.employeesdata.employees.controller.DatabaseErrorController;
import com.employeesdata.employees.exception.BusinessException;
import com.employeesdata.employees.model.Employees;
import com.employeesdata.employees.model.Users;
import com.employeesdata.employees.repo.Emprepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {


    @Autowired
    private Emprepo employeeRepo;



    //Get All Employees
    public List<Employees> getEmp() throws Exception {
        List<Employees> empList = null;
        try {
            empList = new ArrayList<>();
            employeeRepo.findAll().forEach(empList::add);
        }
        catch (Exception e){
            throw new Exception("Something went wrong while fetching all employees.");
        }
        if (empList.isEmpty()){
                throw new NoSuchElementException("No employees Found in the Database. ");
            }
        return empList;


    }

    public Employees findByName(String eName) {

        Employees emp=employeeRepo.findByName(eName);

        return emp;

    }
    public Employees getEmployee(Long id) {
        try{
            return employeeRepo.findById(id).get();
        }
        catch (NoSuchElementException ex){
            throw new NoSuchElementException("No Employee exists with given id. "+ex.getMessage());

        }

    }


    public Employees saveEmployee(Employees empa) throws Exception{
        if(empa.getName().isBlank()){
            throw new BusinessException("601");
        }
        else if(empa.getName().length()<=3){
            throw new BusinessException("602");
        }
        else if(empa.getName().length()>=17){
            throw new BusinessException("603");
        }
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(empa.getEmail());
        if(empa.getEmail().isBlank()){
            throw new BusinessException("604");
        }
        else if(!matcher.matches()){
            throw new BusinessException("605");
        }
        else if(Objects.isNull(empa.getMobile())){
            throw new BusinessException("606");
        }
        else if (String.valueOf(empa.getMobile()).length() != 10) {
            throw new BusinessException("607");
        }
        else if(!String.valueOf(empa.getMobile()).matches("\\d+")){
            throw new BusinessException("608");
        }
        else if(Objects.isNull(empa.getSalary())){
            throw new BusinessException("609");
        }
        else{
            if(empa.equals(null)){
                throw new IllegalArgumentException("employee object is null");
            }
            try{
                return employeeRepo.save(empa);
            }
            catch (Exception e){
                throw new Exception("Error while saving employee.");
            }
        }



    }

    public void updateEmployee(Employees emp){
        employeeRepo.save(emp);
    }
    public void deleteEmployee(Long id) throws BusinessException {
        try{
            employeeRepo.deleteById(id);
        }
        catch (NoSuchElementException ex){
            throw new NoSuchElementException("No Employee exists with given id. "+ex.getMessage());

        }
    }
    public void deleteRecord(Employees emp){
        employeeRepo.delete(emp);
    }





}
