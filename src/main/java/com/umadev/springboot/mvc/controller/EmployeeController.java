package com.umadev.springboot.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.umadev.springboot.mvc.entity.Employee;
import com.umadev.springboot.mvc.service.EmployeeService;

@Controller //Return HTML, while @RestController return JSON
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	private EmployeeService employeeService;
	
	@Autowired //Optional since there is only one constructor
	public EmployeeController( EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee>  theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees"; 
	}

	// add mapping for "/showFormForAdd"

	@GetMapping("/showFormForAdd")
	public String showFormForAdd (Model theModel) {

		Employee  theEmployee = new Employee();
		// add to the spring model, thymeleaf template will access this data 
		// for binding data
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form"; 
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate (@RequestParam("employeeId") int theId, Model theModel) {
		
		// get the employee from the service
		Employee  theEmployee = employeeService.findById(theId);
		
		// set employee in the model to pre-populate the form 
		theModel.addAttribute("employee", theEmployee);

		// send over to our form 
		return "employees/employee-form"; 
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
		// Save the employee
		employeeService.save(theEmployee);
		
		// Use redirect to prevent duplicate submissions
		// Redirect to request mapping /employees/list
		return  "redirect:/employees/list";
	}
}
