package com.employeesdata.employees.repo;

import com.employeesdata.employees.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    Users findByUserName(String userName);



}
