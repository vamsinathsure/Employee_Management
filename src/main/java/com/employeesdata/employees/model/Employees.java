package com.employeesdata.employees.model;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.regex.*;

import lombok.*;

import java.util.Objects;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "emstable")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "mobile",nullable = false)
    private Long mobile;
    @Column(name = "salary",nullable = false)
    private Long salary;


    public Employees(String name, String email, long mobile, long salary) {
        super();

        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
