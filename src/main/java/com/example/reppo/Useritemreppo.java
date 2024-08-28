package com.example.reppo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Example;
import com.example.entity.Example2;


@Repository
public interface Useritemreppo extends JpaRepository<Example2, Long> {

	@Modifying 
	@Transactional
	@Query("UPDATE Example2 e SET e.balanceamount = :balanceamount WHERE e.id = :id")
	void savebyid(@Param("balanceamount")float f,  @Param("id")Long id);
	
	
	@Modifying 
	@Transactional
	@Query("UPDATE Example2 e SET e.paidamount = :paidamount WHERE e.example =:exam2")
	void savebyiddd(@Param("paidamount")float paidamount,@Param("exam2")Example exam2);
 
 
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Example2 (paidamount, id,balanceamount) VALUES(:paidamount,:balanceamount,:id)", nativeQuery = true)
	void savebysave(@Param("paidamount") float f,@Param("balanceamount") float g, @Param("id") Long id);

	@Modifying 
	@Transactional
	@Query("UPDATE Example2 e SET e.balanceamount = :balanceamount WHERE e.example =:exam2")
	void savekp(@Param("balanceamount")float f,  @Param("exam2")Example exam2);
	
	
    List<Example2> findByExample(Example example11);
	
	
	
	 

	
	
	
	
	
	
	
	
	
//	@Modifying 
//	@Transactional
//	@Query("UPDATE Example2 e SET e.balanceamount = :balanceamount WHERE e.id = :id")
//	void savesave(@Param("balanceamount")float f,  @Param("id")Long id);

 
	
	

}
