package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Role;
import com.app.pojos.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUserEmailAndPassword(String userEmail,String password);
	
	Optional<User> findByUserEmail(String userEmail);
	
	List<User> findByRole(Role role);
}
