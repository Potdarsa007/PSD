package com.app.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Employee;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.User;
import com.app.service.IAdminService;
import com.app.service.IUserService;
import com.razorpay.RazorpayClient;

import dto.BookService;
import dto.GiveFeedback;
import dto.UpdatePassword;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/psd/customer")
@Slf4j
public class CustomerController {

	Logger log = LoggerFactory.getLogger(CustomerController.class);

	
	@Autowired
	IUserService cust_service;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	private JavaMailSender javasender;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerdetails(@PathVariable int id)
	{
		log.info("customer ID::"+id);
		User cust=cust_service.getCustomerById(id);
			return ResponseEntity.status(HttpStatus.OK).body(cust);
	}
	
	@PostMapping("/order")
	public String createOrder(@RequestBody Map<String ,Object> data) throws Exception {
		System.out.println(data);
		System.out.println("hey order creating!!!!");
		int amt=Integer.parseInt(data.get("amount").toString());
		var client=new RazorpayClient("rzp_test_h3M6Y3VVHWy4oR", "wU8o0qnNBBrjOUmC4N7DKTuM");
		
		
		JSONObject options = new JSONObject();
		options.put("amount", amt*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		//creating an order
		com.razorpay.Order order = client.Orders.create(options);
		System.out.println(order);
		
		return order.toString();
	}
	
	
	@PostMapping("/newaccount")
	public ResponseEntity<?> createNewCustomerAccount(@RequestBody @Valid User cust)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		User customer=cust_service.insertNewCustomer(cust);
		log.info("Customer details :: "+customer);
		//c.f. implements future and completion stage
		//two methods in c.f. runAsync and supplyAsync
		//runAsync takes runnable object
		//runAsyn-->Returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool() after it runs the given action.
		CompletableFuture<Void> future=CompletableFuture.runAsync(()->
		{
		msg.setTo(customer.getUserEmail());
		msg.setSubject("Account created Succeffully");
		msg.setText("Dear "+customer.getFirstName()+" "+customer.getLastName() +", \n Your account is Successfully created. Welcome to the team.\n\nRegards\n ServiceJunction");
		javasender.send(msg);
		log.info("msg "+msg);
		});
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id)
	{
		cust_service.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.OK).body("Customer Account is successfully deleted");
	}
	
	
	@PostMapping("/updatepassword")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePassword updatepassword)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		User cust=cust_service.updatePassword(updatepassword);
		msg.setTo(cust.getUserEmail());
		msg.setSubject("Retrieved Password");
		msg.setText("Dear "+cust.getFirstName()+" "+cust.getLastName() +", \n Your password is Updated.\n\nRegards\n ServiceJunction");
		javasender.send(msg);
		return ResponseEntity.status(HttpStatus.OK).body(cust);
	}
	
	@PostMapping("/bookservice")
	public ResponseEntity<?> bookService(@RequestBody BookService bookService)
	{
		SimpleMailMessage cust_msg = new SimpleMailMessage();
		SimpleMailMessage emp_msg = new SimpleMailMessage();
		Employee employee=adminService.assignEmployee(bookService);
		User cust=cust_service.getCustomerByEmail(bookService.getUserEmail());
		CompletableFuture<Void> future=CompletableFuture.runAsync(()->
		{
		cust_msg.setTo(cust.getUserEmail());
		cust_msg.setSubject("Book Service");
		cust_msg.setText("Dear "+cust.getFirstName()+" "+cust.getLastName() +", \nYour request has been processed.\nEmployee assigned for your service\n"+employee+"\nHappy Service!! \n\nRegards\nTeam PSD.");
		javasender.send(cust_msg);
		
		emp_msg.setTo(employee.getEmpEmail());
		emp_msg.setSubject("New Assigned Service");
		emp_msg.setText("Dear "+employee.getFirstName()+" "+employee.getLastName() +", \nPlease find the customer details for the requested service\n"+cust+"\nHappy Service!! \n\nRegards\nTeam PSD.");
		javasender.send(emp_msg);
		});
		return ResponseEntity.status(HttpStatus.OK).body(employee);
			
	}
	
	@GetMapping("/completedorders/{id}")
	public ResponseEntity<?> getAllCompletedOrders(@PathVariable(name="id") int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getOrders(OrderStatus.COMPLETED,customerId));
	}
	
	@GetMapping("/uncompletedorders/{id}")
	public ResponseEntity<?> getAllPendingOrders(@PathVariable(name="id") int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getOrders(OrderStatus.PENDING,customerId));
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getAllOrders(@PathVariable(name="id") int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getAllOrders(customerId));
	}
	
	@GetMapping("/feedbackorders/{id}")
	public ResponseEntity<?> getAllFeedbackNotGiven(@PathVariable(name="id") int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.getFeedbackNotGivenOrders(OrderStatus.COMPLETED,false,customerId));
		
	}
	
	@GetMapping("/cancelservice/{id}")
	public ResponseEntity<?> cancelService(@PathVariable(name="id") int orderId)
	{
		SimpleMailMessage emp_msg = new SimpleMailMessage();
		Order order=cust_service.cancelService(orderId);
		Employee employee=order.getEmp();
		emp_msg.setTo(employee.getEmpEmail());
		emp_msg.setSubject("Cancel Service");
		emp_msg.setText("Dear "+employee.getFirstName()+" "+employee.getLastName() +", \nRequest cancelled for the following customer\n"+order.getUser()+"\n\nRegards\nServiceJunction");
		
		return ResponseEntity.status(HttpStatus.OK).body("Request sucessfully cancelled");
	}
	
	@GetMapping("/generatedbill/{id}")
	public String GenerateBill(@PathVariable(name="id") int orderId) throws Exception
	
	{
		Order ord= cust_service.PayService(orderId);
		double amt=(ord.getAmount());
		var client=new RazorpayClient("rzp_test_h3M6Y3VVHWy4oR", "wU8o0qnNBBrjOUmC4N7DKTuM");
		
		
		JSONObject options = new JSONObject();
		options.put("amount", amt*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		//creating an order
		com.razorpay.Order order = client.Orders.create(options);
		System.out.println(order);
		
		return order.toString();
		
	}
	
	
	@PostMapping("/feedback")
	public ResponseEntity<?> giveFeedback(@RequestBody GiveFeedback feedback)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cust_service.giveFeedback(feedback));
	}
	
}
