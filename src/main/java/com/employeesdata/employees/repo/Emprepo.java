package com.employeesdata.employees.repo;

import com.employeesdata.employees.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Emprepo extends CrudRepository<Employees, Long> {

    Employees findByName(String name);


}
