package com.example.reppo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
 

@Repository
public interface Addemployee extends JpaRepository<Employee, Long> {
	

	@Modifying 
	@Transactional
	@Query("UPDATE Employee e SET e.leaveBalance = :leaveBalance WHERE e.employeeid =:employeeid")
	void savekt(@Param("leaveBalance")Long leaveBalance,@Param("employeeid")Long id);
}
