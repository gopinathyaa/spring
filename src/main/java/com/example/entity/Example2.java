package com.example.entity;
 

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example2 {
	
	
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paidid;
 

//	@Min(value = 500, message = "The paid amount must be at least 500")
	 @Size(min = 500, message ="The paid amount must be at least 500")
	 private float paidamount;
    
 
    private float balanceamount;
    
     
    
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonBackReference
    private Example example;


}
