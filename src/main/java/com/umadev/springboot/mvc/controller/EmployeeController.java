package com.umadev.springboot.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umadev.springboot.mvc.entity.Employee;
import com.umadev.springboot.mvc.service.EmployeeService;

@Controller //Return HTML, while @RestController return JSON
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	private EmployeeService employeeService;
	
	public EmployeeController( EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee>  theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "list-employees"; 
	}
}
