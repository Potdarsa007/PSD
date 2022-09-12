package com.app.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Employee;
import com.app.pojos.Order;

import dto.EmployeeDetails;
import dto.UpdatePassword;

public interface IEmployeeService {

	Employee addEmployee(EmployeeDetails employee);

	void deleteEmployee(int id);

	Employee getEmployeeById(int id);

	//Employee authenticateEmployee(String email, String password);

	Employee updateEmployee(Employee emp);

	Employee getEmpByEmail(String email);

	//Employee updatePassword(UpdatePassword updatepassword);

	List<Order> getAllUncompletedOrders(int empId);
}
