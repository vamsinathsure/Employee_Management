package com.employeesdata.employees;

import com.employeesdata.employees.repo.Emprepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class EmployeesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}
	@Autowired
	private Emprepo employeeRepository;


	@Override
	public void run(String... args) throws Exception {
		//Employees emp = new Employees();
		//emp.setName("vamsi");
		//emp.setEmail("vamsi@maik");
		//emp.setMobile(9888);
		//emp.setSalary(9000);
		//employeeRepository.save(emp);


	}
}
