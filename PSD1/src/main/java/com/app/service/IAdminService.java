package com.app.service;

import java.util.List;

import com.app.pojos.Employee;
import com.app.pojos.Profession;
import com.app.pojos.User;

import dto.BookService;

public interface IAdminService {

	public List<Employee> getVerifiedEmps();
	
	public List<Employee> getUnverifiedEmps();
	
	public Profession addProfession(Profession prof);
	
	List<User> getAllCustomers();
	
	public User getAdminById(int id);
	
	public User updateAdmin(User admin);
	
	public Employee assignEmployee(BookService bookService);
	
}
