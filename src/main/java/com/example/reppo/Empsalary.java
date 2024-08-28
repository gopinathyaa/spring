package com.example.reppo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Employee;
import com.example.entity.EmployeeSalary;



@Repository
public interface Empsalary extends JpaRepository<EmployeeSalary, Long> {
	
 List<EmployeeSalary> findByEmployee(Employee emp);
	
}
