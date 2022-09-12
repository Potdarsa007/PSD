package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cust_exception.UserNotFoundException;
import com.app.dao.EmployeeRepository;
import com.app.dao.FeedbackRepository;
import com.app.pojos.Employee;
import com.app.pojos.Profession;
import com.app.pojos.User;
import com.app.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/psd/admin")
@Slf4j
public class AdminController {

	Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	@GetMapping("/getallverifiedemployees")
	public ResponseEntity<?> getAllVerifiedEmps()
	{
		List<Employee> empList=adminService.getVerifiedEmps();
			return ResponseEntity.status(HttpStatus.OK).body(empList);
	
	}
	
	@GetMapping("/getallunverifiedemployees")
	public ResponseEntity<?> getAllUnverifiedEmps()
	{
		List<Employee> empList=adminService.getUnverifiedEmps();
		
			return ResponseEntity.status(HttpStatus.OK).body(empList);
		
	}
	
	@PostMapping("/addprofession")
	public ResponseEntity<?> addProfession(@RequestBody @Valid Profession profession)
	{
		Profession prof=adminService.addProfession(profession);
		log.info("Profession :: "+prof);
		return ResponseEntity.status(HttpStatus.OK).body(prof);
	}
	
	@GetMapping("/getallcustomers")
	public ResponseEntity<?> getAllCustomers()
	{
		List<User> custList=adminService.getAllCustomers();
			return ResponseEntity.status(HttpStatus.OK).body(custList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAdminById(@PathVariable int id)
	{
		log.info("customer ID::"+id);
		User cust=adminService.getAdminById(id);
			return ResponseEntity.status(HttpStatus.OK).body(cust);
	}
	
	
	@GetMapping("/employeeverified/{id}")
	public ResponseEntity<?> employeeVerified(@PathVariable int id)
	{
		Employee emp=empRepo.findById(id).orElseThrow(()->new UserNotFoundException("Employee not found"));
		emp.setVerified(true);
		empRepo.save(emp);
		return ResponseEntity.status(HttpStatus.OK).body("Employee Verfication Done");
	}
	
	@GetMapping("/getallfeedbacks")
	public ResponseEntity<?> getAllFeedbacks()
	{
		return ResponseEntity.status(HttpStatus.OK).body(feedbackRepo.findAll());
	}
	
}

