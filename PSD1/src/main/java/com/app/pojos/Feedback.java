package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Feedback extends BaseEntity{
	
	//@Max(value=5)
	//@Min(value=1)
	@NotNull
	private String customerName;
	
	@NotNull
	private String rating;
	
	@Column(length=500)
	@Length(min = 20, max = 500, message = "Invalid length of serviceName")
	@NotBlank
	private String feedback;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="emp_id",nullable=false)
	@JsonIgnore
	private Employee employee;

	public Feedback(String customerName, String rating,
			@Length(min = 20, max = 500, message = "Invalid length of serviceName") @NotBlank String feedback) {
		super();
		this.customerName = customerName;
		this.rating = rating;
		this.feedback = feedback;
	}

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(String customerName, String rating,
			@Length(min = 20, max = 500, message = "Invalid length of serviceName") @NotBlank String feedback,
			Employee employee) {
		super();
		this.customerName = customerName;
		this.rating = rating;
		this.feedback = feedback;
		this.employee = employee;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
