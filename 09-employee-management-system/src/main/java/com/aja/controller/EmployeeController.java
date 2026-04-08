package com.aja.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.EmployeeDto;
import com.aja.entity.Employee;
import com.aja.serviceimpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeServiceImpl empService;

	public EmployeeController(EmployeeServiceImpl empService) {
		super();
		this.empService = empService;
	}
	
//	public EmployeeDto saveEmployee(EmployeeDto employee);

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody  EmployeeDto employee)
	{
		EmployeeDto empDto = empService.saveEmployee(employee);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(empDto);
	}
	
	//	public Employee fetchById(long id);

	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> getById(@PathVariable long id)
	
	{
		Employee employee = empService.fetchById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
}
