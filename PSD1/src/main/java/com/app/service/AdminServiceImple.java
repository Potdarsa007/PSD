package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cust_exception.UserNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.EmployeeRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProfessionRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Employee;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Profession;
import com.app.pojos.Role;
import com.app.pojos.User;

import dto.BookService;

@Service
@Transactional
public class AdminServiceImple implements IAdminService{

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	ProfessionRepository proRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AddressRepository addRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public List<Employee> getVerifiedEmps() 
	{
		return empRepo.findByVerified(true);
	}

	@Override
	public List<Employee> getUnverifiedEmps() 
	{
		return empRepo.findByVerified(false);
	}

	@Override
	public Profession addProfession(Profession prof) 
	{
		return proRepo.save(prof);
	}

	@Override
	public List<User> getAllCustomers() 
	{
		
		return userRepo.findByRole(Role.CUSTOMER);
	}
	
	@Override
	public User getAdminById(int id) 
	{
		User admin=userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		if(admin.getRole().equals(Role.ADMIN))
			//return new UserDetails(admin.getFirstName(),admin.getLastName(),admin.getUserEmail(),admin.getPassword(),admin.getContactNumber(),admin.getAddress());
			return admin;
		else
			throw new UserNotFoundException("No Admin Found");
	}
	
	@Override
	public User updateAdmin(User ad) 
	{
		User admin=userRepo.findById(ad.getId()).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		ad.setRole(Role.ADMIN);
		ad.getAddress().setId(admin.getAddress().getId());
		addRepo.save(ad.getAddress());
		return userRepo.save(ad);
		/*User admin=userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		System.out.println(admin);
		addRepo.save(ad.getAddress());
		admin.setFirstName(ad.getFirstName());
		admin.setContactNumber(ad.getContactNumber());
		admin.setLastName(ad.getLastName());
		admin.setUserEmail(ad.getUserEmail());
		admin.setAddress(ad.getAddress());
		System.out.println(admin);
		userRepo.save(admin);
		return getAdminById(admin.getId());*/
		
	}

	@Override
	public Employee assignEmployee(BookService bookService) 
	{
		System.out.println("ID::"+bookService.getUserEmail());
		User customer=userRepo.findByUserEmail(bookService.getUserEmail()).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		List<Employee> emps=empRepo.getEmpToBeAssign(true,true,customer.getAddress().getCity(),bookService.getProfessionName().toUpperCase());
		if(emps.size()>0)
		{
			Random rand = new Random();
			Employee emp= emps.get(rand.nextInt(emps.size()));
			Order order=new Order(LocalDate.now(),emp.getProfession().getProfessionName(),emp.getProfession().getBasicCharge(),OrderStatus.PENDING,false,customer,emp);
			orderRepo.save(order);
			emp.setAvailable(false);
			empRepo.save(emp);
			return emp;
		}
		
		throw new UserNotFoundException("No Employee available !!");
	}

}
