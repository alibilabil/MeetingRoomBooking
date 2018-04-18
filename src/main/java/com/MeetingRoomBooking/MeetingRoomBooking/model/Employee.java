package com.MeetingRoomBooking.MeetingRoomBooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmployee;
	private String name;
	private String email;
	private String noTelepon;
	private String department;
	private String username;
	private String password;
	private String role;
	
	
	
	
	public Employee(Integer idEmployee, String name, String department, String username, String password, String role,String email,String noTelepon) {
		super();
		this.idEmployee = idEmployee;
		this.name = name;
		this.department = department;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email= email;
		this.noTelepon=noTelepon;
		
	}
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}





	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNoTelepon() {
		return noTelepon;
	}


	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}
	
	
	
	
	
	
	
	
	
}
