package com.app.controller;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.ContactUs;
import com.app.pojos.User;
import com.app.service.IUserService;

import dto.AuthenticateUser;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/psd/user")
@Slf4j
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	IUserService cust_service;
	
	@Autowired
	private JavaMailSender javasender;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateCustomer(@RequestBody @Valid AuthenticateUser customer)
	{
		log.info(customer.getUserEmail()+"::"+customer.getPassword());
		User cust=cust_service.authenticateCustomerAdmin(customer.getUserEmail(),customer.getPassword());
			return ResponseEntity.status(HttpStatus.OK).body(cust);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session)
	{
		session.invalidate();
		log.info("Customer Logout");
		return ResponseEntity.status(HttpStatus.OK).body("Customer successfully logout");
	}
	
	@PostMapping("/updateaccount")
	public ResponseEntity<?> updateUser(@RequestBody @Valid User cust)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		User customer=cust_service.updateCustomer(cust);
		log.info("user updated details :: "+customer);
		CompletableFuture<Void> future=CompletableFuture.runAsync(()->
		{
		msg.setTo(customer.getUserEmail());
		msg.setSubject("Account updated Succeffully");
		msg.setText("Dear "+customer.getFirstName()+" "+customer.getLastName() +", \n Your account is Successfully updated.\n\nRegards\n ServiceJunction");
		javasender.send(msg);
		});
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@PostMapping("/contactus")
	public ResponseEntity<?> ContactUsDetails(@RequestBody @Valid ContactUs contactus)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		String contact=cust_service.contactUsDetailsSave(contactus);
		log.info("user updated details :: "+contactus.getEmail());
		CompletableFuture<Void> future=CompletableFuture.runAsync(()->
		{
		msg.setTo(contactus.getEmail());
		msg.setSubject("Query under process");
		msg.setText("Dear "+contactus.getName() +", \nThank you for writing into us today. We will process your query and get back to you soon.\n\nRegards\nTeamServiceJunction");
		javasender.send(msg);
		});
		return ResponseEntity.status(HttpStatus.OK).body(contact);
	}
	
	@GetMapping("/getallprofessions")
	public ResponseEntity<?> getAllProfessions()
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getProfessions());
	}
	
	@GetMapping("/getbasiccharge/{professionName}")
	public ResponseEntity<?> getBasicCharge(@PathVariable String professionName)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getBasics(professionName));
	}
	
	@GetMapping("/forgetpassword/{email}")
	public ResponseEntity<?> forgetPassword(@PathVariable String email )
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		User cust=cust_service.getCustomerByEmail(email);
		log.info("Successfully fetched the customer details");
		msg.setTo(cust.getUserEmail());
		msg.setSubject("Retrieved Password");
		msg.setText("Dear "+cust.getFirstName()+" "+cust.getLastName() +", \n Your password is "+cust.getPassword()+"\n\nRegards\n ServiceJunction");
		javasender.send(msg);
		return ResponseEntity.status(HttpStatus.OK).body(cust);
		
	}
	
	
}
