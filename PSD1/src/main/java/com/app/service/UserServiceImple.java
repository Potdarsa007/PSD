package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cust_exception.OrderCompletedException;
import com.app.cust_exception.OrderFeedbackException;
import com.app.cust_exception.UserNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.ContactUsRepository;
import com.app.dao.EmployeeRepository;
import com.app.dao.FeedbackRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProfessionRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Address;
import com.app.pojos.ContactUs;
import com.app.pojos.Employee;
import com.app.pojos.Feedback;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Profession;
import com.app.pojos.User;
import com.app.pojos.Role;

import dto.GiveFeedback;
import dto.UpdatePassword;

@Service
@Transactional
public class UserServiceImple implements IUserService{

	@Autowired
	UserRepository user_repo;
	
	@Autowired
	AddressRepository add_repo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Autowired
	ProfessionRepository profRepo;
	
	@Autowired
	ContactUsRepository contactRepo;
	
	@Override
	public User authenticateCustomerAdmin(String email, String password) 
	{
		return user_repo.findByUserEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException("No User Found !!"));
	}

	@Override
	public User getCustomerById(int id) 
	{
		User customer=user_repo.findById(id).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		if(customer.getRole().equals(Role.CUSTOMER))
			//return new UserDetails(customer.getFirstName(),customer.getLastName(),customer.getUserEmail(),customer.getPassword(),customer.getContactNumber(),customer.getAddress());
			return customer;
		else
			throw new UserNotFoundException("No Customer Found");
	}

	@Override
	public User insertNewCustomer(User customer) 
	{
		Address add=add_repo.save(customer.getAddress());
		User cust=new User(customer.getFirstName(),customer.getLastName(),customer.getUserEmail(),customer.getPassword(),customer.getContactNumber(),Role.CUSTOMER);
		cust.setAddress(add);
		return user_repo.save(cust);
	}

	@Override
	public void deleteCustomer(int id) 
	{
		orderRepo.updateCustId(id);
		user_repo.deleteById(id);
	}

	@Override
	public User updateCustomer(User cust) 
	{
		User customer=user_repo.findById(cust.getId()).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		cust.getAddress().setId(customer.getAddress().getId());
		add_repo.save(cust.getAddress());
		return user_repo.save(cust);
		/*User customer=user_repo.findById(cust.getId()).orElseThrow(()->new UserNotFoundException("No User Found !!"));
		if(customer.getRole().equals(Role.CUSTOMER))
		{
			System.out.println(customer);
			add_repo.save(cust.getAddress());
			customer.setFirstName(cust.getFirstName());
			customer.setContactNumber(cust.getContactNumber());
			customer.setLastName(cust.getLastName());
			customer.setUserEmail(cust.getUserEmail());
			customer.setAddress(cust.getAddress());
			System.out.println(customer);
			user_repo.save(customer);
			return getCustomerById(customer.getId());
		}
		throw new UserNotFoundException("No Customer Found");*/
		
	}

	@Override
	public User getCustomerByEmail(String email) 
	{
		return user_repo.findByUserEmail(email).orElseThrow(()->new UserNotFoundException("No User Found !!"));
	}

	@Override
	public User updatePassword(UpdatePassword updatepassword) 
	{
		User cust=authenticateCustomerAdmin(updatepassword.getUserEmail(),updatepassword.getOldPassword());
		cust.setPassword(updatepassword.getNewPassword());
		return user_repo.save(cust);
	}


	@Override
	public List<Order> getOrders(OrderStatus status,int custId) 
	{
		return orderRepo.findByOrderStatusAndUserId(status,custId);
	}
	
	@Override
	public List<Order> getFeedbackNotGivenOrders(OrderStatus status,boolean feedback,int custId) 
	{
		return orderRepo.findByOrderStatusAndFeedbackGivenAndUserId(status,feedback,custId);
	}

	@Override
	public List<Order> getAllOrders(int id) 
	{
		return orderRepo.findByUserId(id);
	}
	
	@Override
	public Order cancelService(int orderId) 
	{
		Order order= orderRepo.findById(orderId).orElseThrow(()->new UserNotFoundException("Order not Found"));
		if(order.getOrderStatus().equals(OrderStatus.PENDING))
		{
			Employee emp=order.getEmp();
			emp.setAvailable(true);
			empRepo.save(emp);
			orderRepo.deleteById(orderId);
			return order;
		}
		throw new OrderCompletedException("Order already completed");
	}

	@Override
	public String serviceCompleted(int orderId) 
	{
		Order order= orderRepo.findById(orderId).orElseThrow(()->new UserNotFoundException("Order not Found"));
		if(order.getOrderStatus().equals(OrderStatus.PENDING))
		{
			Employee emp=order.getEmp();
			emp.setAvailable(true);
			empRepo.save(emp);
			order.setOrderStatus(OrderStatus.COMPLETED);;
			//order.setAmount(emp.getProfession().getBasicCharge());
			orderRepo.save(order);
			return "Service Completed !!";
		}
		throw new OrderCompletedException("Order already completed");
	}

	@Override
	public String giveFeedback(GiveFeedback feedback) 
	{
		Order order=orderRepo.findById(feedback.getOrderId()).orElseThrow(()->new UserNotFoundException("Order not Found"));
		Feedback fdb=new Feedback(feedback.getCustomerName(),feedback.getRating(),feedback.getFeedback());
		if(!order.isFeedbackGiven())
		{
			fdb.setEmployee(order.getEmp());
			order.setFeedbackGiven(true);
			orderRepo.save(order);
			feedbackRepo.save(fdb);
			return "Successfully submitted !!";
		}
		throw new OrderFeedbackException("FeedBack already given");
	}

	@Override
	public List<Profession> getProfessions() {
		
		return profRepo.findAll();
	}	
	
	@Override
	public double getBasics(String professionName)
	{
		double basicCharge=profRepo.findByProfessionName(professionName).getBasicCharge();
		return basicCharge;
	}

	@Override
	public String contactUsDetailsSave(ContactUs contactus) {

		contactRepo.save(contactus);
		return "Query Saved Successfully";
	}
}
