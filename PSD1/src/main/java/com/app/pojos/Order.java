package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order extends BaseEntity{

	private LocalDate orderDate;
	@NotBlank
	@Length(min = 3, max = 15, message = "Invalid length of serviceName")
	@Column(length=30,name="service_name",nullable=false)
	private String serviceName;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private OrderStatus orderStatus;
	//private boolean completed;
	
	@Column(length=30,name="isfeedback",nullable=false)
	private boolean feedbackGiven;
	
	/*@OneToOne//(cascade=CascadeType.DETACH,orphanRemoval=true)
	@JoinColumn(name="feedback_id")
	Feedback feedback;*/
	
	@ManyToOne
	@JoinColumn(name="cust_id",nullable=true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="emp_id",nullable=true)
	private Employee emp;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(LocalDate orderDate,
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of serviceName") String serviceName,
			double amount, OrderStatus orderStatus, boolean feedbackGiven, User user, Employee emp) {
		super();
		this.orderDate = orderDate;
		this.serviceName = serviceName;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.feedbackGiven = feedbackGiven;
		this.user = user;
		this.emp = emp;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isFeedbackGiven() {
		return feedbackGiven;
	}

	public void setFeedbackGiven(boolean feedbackGiven) {
		this.feedbackGiven = feedbackGiven;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	

	
}
