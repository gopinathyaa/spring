package com.example.entity;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("employees")
@Table(name = "employees")
public class Employee {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long employeeid;
    
	    private String empname;

	    private Long leaveBalance=30L;
	    	    
	    @OneToMany(mappedBy="employee",cascade = CascadeType.ALL)  
	    @JsonManagedReference
	    private List<LeaveRequest> leaveRequests;
	    
}
