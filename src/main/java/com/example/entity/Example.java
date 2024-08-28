package com.example.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Email cannot be null")
    @Size(min = 3, max = 50, message ="Username must be between 3 and 50 characters")
    private String username;
    
    @NotNull(message = "Email cannot be null")
    @Size(min = 6, message ="Password must be at least 6 characters long")
    private String password;
 
    @NotNull(message = "age cannot be null")
    @Min(value = 18,  message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Integer age;
    
    private int year;
    
    private float totalamount;
     
    @Transient
    private String rolename;
    
    
    @OneToMany(mappedBy = "example",cascade = CascadeType.ALL)  
    @JsonManagedReference
    private List<Example2> example2;
    
}
