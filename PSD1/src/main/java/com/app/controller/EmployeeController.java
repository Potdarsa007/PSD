package com.app.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Employee;
import com.app.service.IEmployeeService;

import dto.EmployeeDetails;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/psd/employee")
@Slf4j
public class EmployeeController {
	
	Logger log = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	IEmployeeService empService;
	
	@Autowired
	private JavaMailSender javasender;
	
	@PostMapping("/addnewemployee")
	public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDetails details) throws IOException 
	{
			
			Employee emp=empService.addEmployee(details);
			log.info("Employee details :: "+emp);
			SimpleMailMessage msg = new SimpleMailMessage();
			CompletableFuture<Void> future=CompletableFuture.runAsync(()->
			{
			msg.setTo(emp.getEmpEmail());
			msg.setSubject("Account created Succeffully");
			msg.setText("Dear "+emp.getFirstName()+" "+emp.getLastName() +",\nYour details are successfully saved. We will get back to you for the next step. Looking forward to have you in our family \nHappy Service!!.\n\nRegards\n TeamServiceJunction");
			javasender.send(msg);
			});
			return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id)
	{
		empService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body("Employee Account is successfully deleted");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeDetails(@PathVariable int id)
	{
		log.info("customer ID::"+id);
		Employee emp=empService.getEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
	
	
	@PutMapping("/updateaccount")
	public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee employee)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		Employee emp=empService.updateEmployee(employee);
		log.info("Employee updated details :: "+employee);
		msg.setTo(employee.getEmpEmail());
		msg.setSubject("Account updated Succeffully");
		msg.setText("Dear "+emp.getFirstName()+" "+emp.getLastName() +", \n Your account is Successfully updated.\n\nRegards\n ServiceJunction");
		javasender.send(msg);
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
	
	
	@GetMapping("/pendingorders/{id}")
	public ResponseEntity<?> getAllPendingOrders(@PathVariable(name="id") int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(empService.getAllUncompletedOrders(customerId));
	}
	
}
