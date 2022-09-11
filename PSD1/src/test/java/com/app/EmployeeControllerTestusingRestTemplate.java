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

import com.app.pojos.Employee;

import dto.UpdatePassword;



//Integration Test : complete end to end testing
//creates a web app context (SC) using any available random free port.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTestusingRestTemplate {
	//randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;
	// Abstraction of REST client to be used in test scenario
	@Autowired
	private TestRestTemplate template;


	@Test
	public void testEmployeeConrollerGetEmployeeDetails()  {

		ResponseEntity<Employee> response = template.getForEntity
				("http://localhost:" + serverPort + "/api/psd/employee/4", Employee.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getFirstName(),"radha");
	}
	
	@Test
	public void testCustomerControllerbookService()  {
		
		HttpEntity<UpdatePassword> request=new HttpEntity<>(new UpdatePassword("radhajay7@gmail.com","rj@123","rj@456"));
		ResponseEntity<Employee> response = template.postForEntity
				("http://localhost:" + serverPort + "/api/psd/employee/updatepassword",request ,Employee.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}

}
