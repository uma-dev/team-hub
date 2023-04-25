package com.umadev.springboot.mvc.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.umadev.springboot.mvc.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Integer>{
    
}
