package com.MeetingRoomBooking.MeetingRoomBooking.dao;

import java.util.List;

import com.MeetingRoomBooking.MeetingRoomBooking.model.Employee;

public interface EmployeeDao {
	List <Employee> listEmployee();
	Employee saveOrUpdate (Employee employee);
	Employee getIdEmployee(Integer idEmployee);
	void delete(Integer idEmployee);
	Employee login(String username, String password);
	Employee getUserByUsername(String username);
}
