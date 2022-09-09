package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.pojos.Address;
import com.app.pojos.Employee;
import com.app.pojos.Role;
import com.app.pojos.User;

import dto.BookService;



//Integration Test : complete end to end testing
//creates a web app context (SC) using any available random free port.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerControllerTestusingRestTemplate {
	//randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;
	// Abstraction of REST client to be used in test scenario
	@Autowired
	private TestRestTemplate template;

	@Test
	public void testTestConroller() throws Exception {

		String response = template.getForObject
				("http://localhost:" + serverPort + "/", String.class);
		assertEquals(response, "Test Message...");
	}
	
	@Test
	public void testCustomerControllerGetCustomerByID()  {

		ResponseEntity<User> response = template.getForEntity
				("http://localhost:" + serverPort + "/api/psd/customer/3", User.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getFirstName(),"Chetan");
	}
	
	@Test
	public void testCustomerControllerCreateNewCustomerAcc()  {

		Address add=new Address("4R","Kanada Market","delhi","delhi","11234");
		HttpEntity<User> request=new HttpEntity<>(new User("Chetna","Patil","teampsd@gmail.com","chetna@123",99898,Role.CUSTOMER,add));
		ResponseEntity<User> response = template.postForEntity
				("http://localhost:" + serverPort + "/api/psd/customer/newaccount", request,User.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getFirstName(),"Chetna");
	}
	
	@Test
	public void testCustomerControllerbookService()  {
		
		HttpEntity<BookService> request=new HttpEntity<>(new BookService("chetankumar4045@gmail.com","PAINTING"));
		ResponseEntity<Employee> response = template.postForEntity
				("http://localhost:" + serverPort + "/api/psd/customer/bookservice",request ,Employee.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getProfession().getProfessionName(),"PAINTING");
	}
	
}
