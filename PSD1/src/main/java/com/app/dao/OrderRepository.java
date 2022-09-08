package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Order;
import com.app.pojos.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
	List<Order> findByOrderStatusAndUserId(OrderStatus status,int id);
	
	List<Order> findByOrderStatusAndEmpId(OrderStatus status,int id);
	
	List<Order> findByOrderStatusAndFeedbackGivenAndUserId(OrderStatus status,boolean feedback,int id);
	
	List<Order> findByUserId(int id);
	
	@Modifying
	@Query("Update Order o set o.user.id=null where user.id=:id")
	int updateCustId(@Param(value="id") int id);
	
	@Modifying
	@Query("Update Order o set o.emp.id=null where emp.id=:id")
	int updateEmpId(@Param(value="id") int id);
	
}
