package com.sonu.collections.dao;

import java.util.List;

import com.sonu.collections.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
	
	
	//Named Query
	
	public List<Object[]> getMaxSalaryByDept(List<String> deptNames);

}
