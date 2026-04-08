package com.aja.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aja.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("from Employee where IsDeleted=false")
	public List<Employee> fetchAllRecords();
	@Query("from Employee where IsDeleted=false and id=:eid ")
	public Optional<Employee> getById(@Param("eid")  long id);
	
	public boolean existsByEmail(String email);
}
