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
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave_req")
public class LeaveRequest {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Long leaveDays;
	    private String reason;
	    
	    @FutureOrPresent(message = "From date must be today or in the future.")
	    private Date fromDate;
	    
	    @FutureOrPresent(message = "From date must be today or in the future.")
	    private Date toDate;  
	    
	    
	    @ManyToOne(fetch =FetchType.LAZY)
	    @JoinColumn(name = "employeeid")
	    @JsonBackReference
	    private Employee  employee;	 	  
	    
}
