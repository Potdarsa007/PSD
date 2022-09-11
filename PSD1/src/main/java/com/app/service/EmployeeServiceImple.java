package com.app.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.cust_exception.UserNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.EmployeeRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProfessionRepository;
import com.app.pojos.Address;
import com.app.pojos.Employee;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Profession;

import dto.EmployeeDetails;
import dto.UpdatePassword;

@Service
@Transactional
public class EmployeeServiceImple implements IEmployeeService{

	@Autowired
	ProfessionRepository profRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	AddressRepository addRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Value("${file.upload.location}")
	private String location;
	
	@Override
	public Employee addEmployee(EmployeeDetails employeedto)
	{
	
		Employee emp=new Employee(false,true);
		Address add=new Address();
		BeanUtils.copyProperties(employeedto, emp);
		BeanUtils.copyProperties(employeedto, add);
		emp.setAddress(add);
		Profession prof=profRepo.findByProfessionName(employeedto.getProfessionName());
		emp.setProfession(prof);
		//emp.setAdharFileName(file.getOriginalFilename());
		addRepo.save(add);
		empRepo.save(emp);
		//file.transferTo(new File(location, file.getOriginalFilename()));
		return emp;
	}

	@Override
	public void deleteEmployee(int id) 
	{
		orderRepo.updateEmpId(id);
		empRepo.deleteById(id);
	}
	
	@Override
	public Employee getEmployeeById(int id) 
	{
		Employee emp=empRepo.findById(id).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		//EmployeeDetails employeedto=new EmployeeDetails();
		//BeanUtils.copyProperties(emp,employeedto );
		//BeanUtils.copyProperties(emp.getAddress(), employeedto);
		//BeanUtils.copyProperties(emp.getProfession(), employeedto);
			//System.out.println(employeedto);
			//return employeedto;
		return emp;
	}
	
	/*@Override
	public Employee authenticateEmployee(String email, String password) 
	{
		return empRepo.findByEmpEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException("No User Found !!"));
	}*/
	
	@Override
	public Employee updateEmployee(Employee emp) 
	{
		Employee employee=empRepo.findById(emp.getId()).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		emp.getAddress().setId(employee.getAddress().getId());
		addRepo.save(emp.getAddress());
		return empRepo.save(emp);
	}

	@Override
	public Employee getEmpByEmail(String email) 
	{
		return empRepo.findByEmpEmail(email).orElseThrow(()->new UserNotFoundException("No Employee Found !!"));
	}
	
	/*@Override
	public Employee updatePassword(UpdatePassword updatepassword) 
	{
		Employee emp=authenticateEmployee(updatepassword.getUserEmail(),updatepassword.getOldPassword());
		emp.setPassword(updatepassword.getNewPassword());
		return empRepo.save(emp);
	}*/
	
	@Override
	public List<Order> getAllUncompletedOrders(int empId) 
	{
		return orderRepo.findByOrderStatusAndEmpId(OrderStatus.PENDING,empId);
	}

}
