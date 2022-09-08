package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contact_us")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContactUs extends BaseEntity{

	@NotBlank
	@Length(min = 3, max = 40, message = "Invalid length of name field")
	@Column(length=40,name="name",nullable=false)
	private String name;
	
	@NotBlank
	@Email
	@Column(length=30,name="email",nullable=false)
	private String email;
	
	@Column(length=500)
	@Length(min = 10, max = 500, message = "Invalid length of issue field")
	@NotBlank
	private String issue;

	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactUs(@NotBlank @Length(min = 3, max = 40, message = "Invalid length of name field") String name,
			@NotBlank @Email String email,
			@Length(min = 10, max = 500, message = "Invalid length of issue field") @NotBlank String issue) {
		super();
		this.name = name;
		this.email = email;
		this.issue = issue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	

	
}

