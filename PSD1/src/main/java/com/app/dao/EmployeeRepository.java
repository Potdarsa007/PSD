package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	List<Employee> findByVerified(Boolean verified);
	
	
	//The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries
	@Modifying
	@Query(value="select e from Employee e where e.verified=:v and e.available=:a and e.address.city=:c and e.profession.professionName=:p")
	List<Employee> getEmpToBeAssign(@Param(value="v") Boolean v,@Param(value="a") Boolean a ,@Param(value="c") String c,@Param(value="p") String p);
	
	//Optional<Employee> findByEmpEmailAndPassword(String userEmail,String password);
	
	Optional<Employee> findByEmpEmail(String empEmail);
}
