package com.aja.service;


import java.util.Optional;

import com.aja.dto.EmployeeDto;
import com.aja.entity.Employee;

public interface EmployeeService {
	//register employee information into the database
	public EmployeeDto saveEmployee(EmployeeDto employee);
	//using eid to fetch active records 
	public Employee fetchById(long id);
	
	//
	public void deleteemployee(long eid);
	

}
