package com.example.reppo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Example;
import java.util.List;


@Repository
public interface UserRepository extends  JpaRepository<Example,Long>{

	@Modifying 
	@Transactional
	@Query("UPDATE Example e SET e.age = :age,e.username=:username,e.password=:password,e.totalamount=:totalamount WHERE e.id = :id")
	int savebyidd(@Param("age")Integer age,@Param("username") String username,@Param("password") String password,@Param("totalamount")float totalamount,  @Param("id")Long id);

 
  
//	    @Query("SELECT e FROM Example e WHERE e.totalamount = :amount")
//	    List<Example> findByTotalamount(@Param("amount") float amount);
 
	@Query("SELECT e FROM Example e WHERE e.totalamount BETWEEN :minAmount AND :maxAmount")
	List<Example> findByTotalamount(@Param("minAmount") float minAmount, @Param("maxAmount") float maxAmount);
}
