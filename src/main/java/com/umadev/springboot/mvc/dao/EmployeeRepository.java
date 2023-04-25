package com.umadev.springboot.mvc.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umadev.springboot.mvc.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Integer>{
    
    // Add a method to sort by lastName
    // Spring Data JPA will parse the method name and create appropiate query 
    // the syntax is available in:
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
    public List<Employee> findAllByOrderByLastNameAsc();
}
