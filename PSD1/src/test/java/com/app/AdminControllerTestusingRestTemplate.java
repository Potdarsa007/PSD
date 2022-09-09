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

import com.app.pojos.Profession;
import com.app.pojos.User;


//Integration Test : complete end to end testing
//creates a web app context (SC) using any available random free port.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AdminControllerTestusingRestTemplate {
	//randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;
	// Abstraction of REST client to be used in test scenario
	@Autowired
	private TestRestTemplate template;


	@Test
	public void testCustomerConrollerGetAdminByID()  {

		ResponseEntity<User> response = template.getForEntity
				("http://localhost:" + serverPort + "/api/psd/admin/10",User.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getFirstName(),"Swapnil");
	}
	
	@Test
	public void testCustomerControllerAddProfession()  {
		
		HttpEntity<Profession> request=new HttpEntity<>(new Profession("AC REPAIR",500));
		ResponseEntity<Profession> response = template.postForEntity
				("http://localhost:" + serverPort + "/api/psd/admin/addprofession",request ,Profession.class);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody().getProfessionName(),"AC REPAIR");
	}
}
