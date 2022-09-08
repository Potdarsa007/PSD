package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin_customers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User extends BaseEntity{

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
	@Column(length=30,name="user_email",unique=true,nullable=false)
	private String userEmail;
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	@Column(length = 20, nullable = false)
	private String password;
	@NotNull
	@Column(name="contact_number")
	private long contactNumber;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	//@JsonIgnore
	private Role role;
	//CascadeType.Remove-->deleting the house can delete the door
	@OneToOne(cascade=CascadeType.REMOVE)
	private Address address;
	
	public User(
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of FirstName") String firstName,
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of LastName") String lastName,
			@NotBlank @Email String userEmail,
			@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password") String password,
			long contactNumber, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.password = password;
		this.contactNumber = contactNumber;
		this.role = role;
	}
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	public User(@NotBlank @Length(min = 3, max = 15, message = "Invalid length of FirstName") String firstName,
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of LastName") String lastName,
			@NotBlank @Email String userEmail,
			@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password") String password,
			long contactNumber, Role role, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.password = password;
		this.contactNumber = contactNumber;
		this.role = role;
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




	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public long getContactNumber() {
		return contactNumber;
	}




	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}




	public Role getRole() {
		return role;
	}




	public void setRole(Role role) {
		this.role = role;
	}




	public Address getAddress() {
		return address;
	}




	public void setAddress(Address address) {
		this.address = address;
	}




	@Override
	public String toString() {
		return "Customers_Admin [id ="+getId()+" ,firstName=" + firstName + ", lastName=" + lastName + ", userEmail=" + userEmail
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", role=" + role + "]";
	}

}
