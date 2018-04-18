package com.MeetingRoomBooking.MeetingRoomBooking.controller;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.MeetingRoomBooking.MeetingRoomBooking.dao.BookingDao;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.EmployeeDao;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.RoomDao;
import com.MeetingRoomBooking.MeetingRoomBooking.implement.BookingDaoImpl;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Employee;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;



@Controller
public class AdminController {
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	

	
	
	@RequestMapping(value ="/admin/create/room",method = RequestMethod.GET)
	public String createRoom(Model model,HttpServletRequest request) {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}

		model.addAttribute("navigation", "admin");
		model.addAttribute("admin", true);
		model.addAttribute("create", true);
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("Room", new Room());
		return "FormCreateRoom";
	}
	
	@RequestMapping(value = "/admin/create/room", method = RequestMethod.POST)
	public String saveRoom(Model model, Room room,HttpServletRequest request ) {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("navigation", "admin");
		model.addAttribute("Room", roomDao.saveOrUpdate(room));
		return "redirect:/home";
		
	} 
	
	@RequestMapping(value="/admin/update/room/{idRoom}",method=RequestMethod.GET)
	public String updateRoom(@PathVariable Integer idRoom,Model model,HttpServletRequest request){
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("admin", true);
		model.addAttribute("edit", true);
		model.addAttribute("Room",roomDao.getIdRoom(idRoom));
		return "FormCreateRoom";
		
	}
	
	
	@RequestMapping(value ="/admin/create/employee",method = RequestMethod.GET)
	public String registerEmployee(Model model,HttpServletRequest request) {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("create", true);
		model.addAttribute("navigation", "register");
		model.addAttribute("admin", true);
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("Employee", new Employee());
		return "Register";
	}
	@RequestMapping(value = "/admin/create/employee", method = RequestMethod.POST)
	public String saveEmployee(Model model, Employee employee,HttpServletRequest request ) {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("navigation", "admin");
		
		model.addAttribute("Employee", employeeDao.saveOrUpdate(employee));
		return "redirect:/home";
		
	} 
	
	@RequestMapping(value="/admin/update/employee/{idEmployee}",method=RequestMethod.GET)
	public String updateEmployee(@PathVariable Integer idEmployee,Model model,HttpServletRequest request){
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("edit", true);
		model.addAttribute("admin", true);
		model.addAttribute("Employee",employeeDao.getIdEmployee(idEmployee));
		return "Register";
		
	}
	
	@RequestMapping(value="/admin/listUser",method=RequestMethod.GET)
	public String listEmployee(Model model,HttpServletRequest request){
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("admin", true);
		model.addAttribute("navigation", "listUser");
		model.addAttribute("ListEmployee",employeeDao.listEmployee());
		return "ListUser";
		
	}
	
	@RequestMapping(value="/admin/result",method=RequestMethod.GET)
	public String result(Model model,HttpServletRequest request) throws Exception  {
		Booking book1 = new Booking();
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("admin", true);
		model.addAttribute("navigation", "result");
		model.addAttribute("ListEmployee",employeeDao.listEmployee());
		String canceled="cancel";
		List<Booking> listBooking = bookingDao.listBooking();
		List<Employee> listEmployee = employeeDao.listEmployee();
		List<Room> listRoom = roomDao.listRoom();
		Map countCanceled = new HashMap<>();
		int count = 0;
		int CountBookingPersen=14;
		for(Employee employee : listEmployee){
			for(Booking booking : listBooking){
				if(employee.getIdEmployee().equals(booking.getId_Employee())&&booking.getStatus().equals("cancel")){
					count=count+1;
				}
			}
			countCanceled.put(employee.getIdEmployee(), count);
		
			count=0;
		}
		
		Map countBooking1 = new HashMap<>();
		Map countBooking1Persen = new HashMap<>();
		
		for(Room room : listRoom){
			for(Booking booking : listBooking){
				
				if(room.getIdRoom().equals(booking.getId_Room()))
					count=count+1;
				}
			int persen =(int) (((float)count/(float)bookingDao.count1()) * 100);
			countBooking1Persen.put(room.getRoomName(), persen);
			countBooking1.put(room.getRoomName(), count);
			
			count=0;
			
			
			
			
			
			 
		}
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
		Date date = new Date();
		String  monthAndYear = dateFormat.format(date); 
		if(request.getParameter("showByDate3") != null) {
			monthAndYear = request.getParameter("showByDate3");
		}
		
		Map countBooking2 = new HashMap<>();
		Map countBooking2Persen = new HashMap<>();
		
		for(Room room : listRoom){
			for(Booking booking : listBooking){
				Date a = booking.getStart();
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
				String y= dateFormat2.format(a);
				if(room.getIdRoom().equals(booking.getId_Room()) && y.equals(monthAndYear))
					count=count+1;
				}
			int persen2 =(int) (((float)count/(float)bookingDao.count2(monthAndYear)) * 100);
			countBooking2Persen.put(room.getRoomName(), persen2);
			countBooking2.put(room.getRoomName(), count);
			count=0;
			

		}
		
		DateFormat d = new SimpleDateFormat("yyyy-MM");
		Date a = new Date();
		String  year = d.format(a); 
		if(request.getParameter("showByYear") != null ) {
			year = request.getParameter("showByYear");
			
			
		}
		int p1 =(int) (((float)bookingDao.countJanuari(year+"-01")/(float)bookingDao.count3(year)) * 100);
		int p2 =(int) (((float)bookingDao.countFebruari(year+"-02")/(float)bookingDao.count3(year)) * 100);
		int p3 =(int) (((float)bookingDao.countMaret(year+"-03")/(float)bookingDao.count3(year)) * 100);
		int p4 =(int) (((float)bookingDao.countApril(year+"-04")/(float)bookingDao.count3(year)) * 100);
		int p5 =(int) (((float)bookingDao.countMei(year+"-05")/(float)bookingDao.count3(year)) * 100);
		int p6 =(int) (((float)bookingDao.countJuni(year+"-06")/(float)bookingDao.count3(year)) * 100);
		int p7 =(int) (((float)bookingDao.countJuli(year+"-07")/(float)bookingDao.count3(year)) * 100);
		int p8 =(int) (((float)bookingDao.countAgustus(year+"-08")/(float)bookingDao.count3(year)) * 100);
		int p9 =(int) (((float)bookingDao.countSeptember(year+"-09")/(float)bookingDao.count3(year)) * 100);
		int p10 =(int) (((float)bookingDao.countOktober(year+"-010")/(float)bookingDao.count3(year)) * 100);
		int p11=(int) (((float)bookingDao.countNovember(year+"-011")/(float)bookingDao.count3(year)) * 100);
		int p12=(int) (((float)bookingDao.countDesember(year+"-012")/(float)bookingDao.count3(year)) * 100);
		System.out.println("yyyyyyyyyyyyyyyyy"+year+"mmmmmm"+bookingDao.countJanuari(year+"-01"));
		
		
		model.addAttribute("cm1", bookingDao.countJanuari(year+"-01"));
		model.addAttribute("cmp1", p1);
		model.addAttribute("cm2", bookingDao.countFebruari(year+"-02"));
		model.addAttribute("cmp2", p2);
		model.addAttribute("cm3", bookingDao.countMaret(year+"-03"));
		model.addAttribute("cmp3", p3);
		model.addAttribute("cm4", bookingDao.countApril(year+"-04"));
		model.addAttribute("cmp4", p4);
		model.addAttribute("cm5", bookingDao.countMei(year+"-05"));
		model.addAttribute("cmp5", p5);
		model.addAttribute("cm6", bookingDao.countJuni(year+"-06"));
		model.addAttribute("cmp6", p6);
		model.addAttribute("cm7", bookingDao.countJuli(year+"-07"));
		model.addAttribute("cmp7", p7);
		model.addAttribute("cm8", bookingDao.countAgustus(year+"-08"));
		model.addAttribute("cmp8", p8);
		model.addAttribute("cm9", bookingDao.countSeptember(year+"-09"));
		model.addAttribute("cmp9", p9);
		model.addAttribute("cm10", bookingDao.countOktober(year+"-010"));
		model.addAttribute("cmp10", p10);
		model.addAttribute("cm11", bookingDao.countNovember(year+"-011"));
		model.addAttribute("cmp11", p11);
		model.addAttribute("cm12", bookingDao.countDesember(year+"-012"));
		model.addAttribute("cmp12", p12);
		model.addAttribute("total1", bookingDao.count1());
		model.addAttribute("total2", bookingDao.count2(monthAndYear));
		model.addAttribute("total3", bookingDao.count3(year));
		
		model.addAttribute("countBooking2", countBooking2);
		model.addAttribute("countBooking1", countBooking1);
		model.addAttribute("countBookingPersen", countBooking1Persen);
		model.addAttribute("countBookingPersen2", countBooking2Persen);
		model.addAttribute("countCanceled", countCanceled);	
		model.addAttribute("employee", employeeDao.listEmployee());
		model.addAttribute("listBooking", bookingDao.listBooking());
		
		
		
		
		
		return "Report";
		
	}
	

	@RequestMapping(value="/admin/delete/user/{idEmployee}")
	public String deleteUser(@PathVariable Integer idEmployee,HttpServletRequest request){
		if (request.getSession().getAttribute("eLogin") == null) { 
			return "redirect:/login";
		}
		employeeDao.delete(idEmployee);
		return "redirect:/admin/listUser";	
	}
	

	

	
	
	
	@RequestMapping(value="/admin/delete/room/{idRoom}")
	public String delete(@PathVariable Integer idRoom,HttpServletRequest request){
		if (request.getSession().getAttribute("eLogin") == null) { 
			return "redirect:/login";
		}
		roomDao.delete(idRoom);
		return "redirect:/home";	
	}
	
	
	
	
	
	
}
