package com.example.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalary {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long salaryid;
	
	private float salaryamo;
	
	private float incrementsalary;
	
	private Date  incrementsalaryDate;	
		  
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    @JsonBackReference
    private Employee  employee;	  
}
