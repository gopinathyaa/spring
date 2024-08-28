package com.example.reppo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
import com.example.entity.LeaveRequest;
import java.util.List;


@Repository
public interface LeaveReq  extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findByEmployee(Employee employee);

}
