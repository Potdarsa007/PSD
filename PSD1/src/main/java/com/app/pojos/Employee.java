package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee extends BaseEntity{

	@NotBlank
	@Length(min = 3, max = 15, message = "Invalid length of FirstName")
	@Column(length=30,name="first_name",nullable=false)
	private String firstName;
	@NotBlank
	@Length(min = 3, max = 15, message = "Invalid length of LastName")
	@Column(length=30,name="last_name",nullable=false)
	private String lastName;
	@NotBlank
	@Email
	@Column(length=30,name="emp_email",unique=true,nullable=false)
	private String empEmail;
	//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	//@Column(length = 20, nullable = false)
	//private String password;
	
	@NotNull
	@Column(name="contact_number")
	private long contactNumber;
	@NotNull
	@Max(value=100)
	private int experience;
	//@JsonIgnore is used at field level to mark a property or list of properties to be ignored
	@JsonIgnore
	private boolean verified;
	@JsonIgnore
	private boolean available;
	
	//@JsonIgnore
	//private String adharFileName;
	
	
	//If you use @OnDelete then deleting the door will also delete the house.
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="prof_id",nullable=false)
	private Profession profession;
	
	
	//If you use CascadeType.REMOVE then deleting the house will also delete the door.
	@OneToOne(cascade=CascadeType.REMOVE)
	private Address address;

	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public Employee(@NotBlank @Length(min = 3, max = 15, message = "Invalid length of FirstName") String firstName,
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of LastName") String lastName,
			@NotBlank @Email String empEmail, long contactNumber, @Max(100) int experience, boolean verified,
			boolean available, Profession profession, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empEmail = empEmail;
		this.contactNumber = contactNumber;
		this.experience = experience;
		this.verified = verified;
		this.available = available;
		this.profession = profession;
		this.address = address;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmpEmail() {
		return empEmail;
	}




	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}




	public long getContactNumber() {
		return contactNumber;
	}




	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}




	public int getExperience() {
		return experience;
	}




	public void setExperience(int experience) {
		this.experience = experience;
	}




	public boolean isVerified() {
		return verified;
	}




	public void setVerified(boolean verified) {
		this.verified = verified;
	}




	public boolean isAvailable() {
		return available;
	}




	public void setAvailable(boolean available) {
		this.available = available;
	}




	public Profession getProfession() {
		return profession;
	}




	public void setProfession(Profession profession) {
		this.profession = profession;
	}




	public Address getAddress() {
		return address;
	}




	public void setAddress(Address address) {
		this.address = address;
	}




	@Override
	public String toString() {
		return "Employee Name=" + firstName + " " + lastName + "\nEmployee emailId=" + empEmail
				+ "\nEmployee contactNumber=" + contactNumber + "\nEmployee Experience=" + experience;
	}


	public Employee(boolean verified, boolean available) {
		super();
		this.verified = verified;
		this.available = available;
	}
	
	
	
}
