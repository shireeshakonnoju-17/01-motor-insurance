package com.aja.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aja.dto.EmployeeDto;
import com.aja.entity.Employee;
import com.aja.entity.RegistrationStatus;
import com.aja.exception.ResourceNotFoundException;
import com.aja.repo.EmployeeRepository;
import com.aja.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository eRepo;
	
	Employee mapToEntity(EmployeeDto employeeDto)
	{
		Employee emp = new Employee();
		

		emp.setEmail(employeeDto.getEmail());
		emp.setName(employeeDto.getName());
		emp.setDepartment(employeeDto.getDepartment());
		return emp;
	}
	EmployeeDto mapToDto(Employee employee) {
		
		EmployeeDto empdto = new EmployeeDto();
		
		empdto.setName(employee.getName());
		empdto.setEmail(employee.getEmail());
		empdto.setDepartment(employee.getDepartment());
		
		return empdto;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employee) {

//convert employeedto to entity
		Employee saveEmp = mapToEntity(employee);
		
		
		if(eRepo.existsByEmail(saveEmp.getEmail()))
		{
			throw new ResourceNotFoundException("Email is Not Exist");
		}
		saveEmp.setStatus(RegistrationStatus.pending);
		
		Employee saveEmployee = eRepo.save(saveEmp);
		EmployeeDto mapToDto = mapToDto(saveEmployee);
		
		return mapToDto;
	}
	@Override
	public Employee fetchById(long id) {
		// TODO Auto-generated method stub
		Employee emp = eRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Id is not exist"));
		
		return emp;
	}
	@Override
	public void deleteemployee(long eid) {
		// TODO Auto-generated method stub
		Employee emp = eRepo.findById(eid)
		        .orElseThrow(() -> new ResourceNotFoundException("Id is not exist"));
		emp.setIsDeleted(true);
		eRepo.save(emp);
	}


}
