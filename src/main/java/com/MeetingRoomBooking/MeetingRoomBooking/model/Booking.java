package com.MeetingRoomBooking.MeetingRoomBooking.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;




@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBooking;
	private Integer id_Employee;
	private Integer id_Room;
	private Date start;
	private Date end;
	private String status;
	private String about;
	private String email;
	private String noTelepon;
	private String department;
	private String pic;
	private String room;
	
	
	
	
	
	public Booking(Integer idBooking, Integer id_Employee, Integer id_Room, Date start, Date end, String status,
			String about, String department, String pic,String email,String noTelepon,String room) {
		super();
		this.idBooking = idBooking;
		this.id_Employee = id_Employee;
		this.id_Room = id_Room;
		this.start = start;
		this.end = end;
		this.status = status;
		this.about = about;
		this.department = department;
		this.pic = pic;
		this.email= email;
		this.noTelepon=noTelepon;
		this.room=room;
		
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(Integer idBooking) {
		this.idBooking = idBooking;
	}
	public Integer getId_Employee() {
		return id_Employee;
	}
	public void setId_Employee(Integer id_Employee) {
		this.id_Employee = id_Employee;
	}
	public Integer getId_Room() {
		return id_Room;
	}
	public void setId_Room(Integer id_Room) {
		this.id_Room = id_Room;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
	
	

	
	


	
	
	
	
	
	
	
	
}
