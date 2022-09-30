package com.app.service;

import java.util.List;

import com.app.pojos.ContactUs;
import com.app.pojos.Feedback;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Profession;
import com.app.pojos.User;

import dto.GiveFeedback;
import dto.UpdatePassword;

public interface IUserService {

	public User authenticateCustomerAdmin(String email,String paswword);
	
	public User getCustomerById(int id);
	
	public User insertNewCustomer(User cust);
	
	public void deleteCustomer(int id);
	
	public User updateCustomer(User cust);
	
	public User getCustomerByEmail(String email);
	
	public User updatePassword(UpdatePassword updatepassword);
	
	public List<Order> getOrders(OrderStatus status,int id);
	
	public List<Order> getAllOrders(int id);
	
	public Order cancelService(int orderid);
	
	public String serviceCompleted(int orderid);
	
	public String giveFeedback(GiveFeedback feedback);

	List<Order> getFeedbackNotGivenOrders(OrderStatus status, boolean feedback, int custId);
	
	List<Profession> getProfessions();

	public double getBasics(String professionName);
	
	public String contactUsDetailsSave(ContactUs contactus);

	Order PayService(int orderId);
}
