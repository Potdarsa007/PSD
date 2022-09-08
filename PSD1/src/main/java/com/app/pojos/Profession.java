package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="professions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Profession extends BaseEntity{

	@NotBlank
	@Length(min = 3, max = 15, message = "Invalid length of profName")
	@Column(length=30,name="prof_name",nullable=false,unique=true)
	private String professionName;
	@NotNull
	@Column(name="basic_charge")
	private double basicCharge;
		
	
	
	
	
	public Profession() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Profession(
			@NotBlank @Length(min = 3, max = 15, message = "Invalid length of profName") String professionName,
			double basicCharge) {
		super();
		this.professionName = professionName;
		this.basicCharge = basicCharge;
	}





	public String getProfessionName() {
		return professionName;
	}





	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}





	public double getBasicCharge() {
		return basicCharge;
	}





	public void setBasicCharge(double basicCharge) {
		this.basicCharge = basicCharge;
	}





	@Override
	public String toString() {
		return "Profession [profId="+getId()+ ",profName=" + professionName + ", basicCharge=" + basicCharge + "]";
	}
		
		
}

