package com.MeetingRoomBooking.MeetingRoomBooking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Date;



import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MeetingRoomBooking.MeetingRoomBooking.dao.BookingDao;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.RoomDao;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Employee;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;



@Controller
public class UserController {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private RoomDao roomDao;
	Integer idr;
	String Room;
	
	
	@Autowired
	public BookingDao getBookingDao() {
		return bookingDao;
	}

	

	@RequestMapping(value="/employee/book/room/{idRoom}",method=RequestMethod.GET)
	public String booking2( Model model, HttpServletRequest request,@PathVariable Integer idRoom) {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("employee", true);
		model.addAttribute("idUser",((Employee)request.getSession().getAttribute("employeeLogin")).getIdEmployee());
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		model.addAttribute("departmentUser",((Employee)request.getSession().getAttribute("employeeLogin")).getDepartment());
		model.addAttribute("id_room", roomDao.getIdRoom(idRoom));
		
		idr= idRoom;
		model.addAttribute("idr", idr);
		model.addAttribute("localDate", LocalDate.now());
		Room r = roomDao.getIdRoom(idRoom);
		String roomName= r.getRoomName();
		Room = roomName;
		model.addAttribute("roomName", roomName);
		
		return "FormBooking";
	}
	
	
	@RequestMapping(value = "/employee/book/room/", method = RequestMethod.POST)
	public String saveBook2( Model model, HttpServletRequest request,Booking booking,RedirectAttributes redirectAttributes,BindingResult bindingResult) throws ParseException  {
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date da = new Date();
		
		Integer idRoom = idr;
		String room= Room;
		String s = request.getParameter("start");
		String e = request.getParameter("end");
		Date start = df.parse(s);
		Date end = df.parse(e);
		
		String status = "Booking";
		String about = request.getParameter("about");
		String department = request.getParameter("department");
		Integer id_Employee=Integer.parseInt(request.getParameter("idUser"));
		String pic = ((Employee)request.getSession().getAttribute("employeeLogin")).getName();
		String email=((Employee)request.getSession().getAttribute("employeeLogin")).getEmail();
		String noTelepon = ((Employee)request.getSession().getAttribute("employeeLogin")).getNoTelepon();
		
		
		
		if(bookingDao.cek(start, end, idRoom).isEmpty() ){
			
			if(start.before(da) || end.before(da)){
				model.addAttribute("localDate", LocalDate.now());
				redirectAttributes.addFlashAttribute("message","The date you entered has passed");
				return "redirect:/employee/book/room/"+idr;
			}
			else if(end.before(start)){
				model.addAttribute("localDate", LocalDate.now());
				redirectAttributes.addFlashAttribute("message","The date you entered is incorrect. Start date must be first from end date");
				return "redirect:/employee/book/room/"+idr;
			}
			else if(bindingResult.hasErrors()){
				redirectAttributes.addFlashAttribute("message","aaaa");
				return "redirect:/employee/book/room/"+idr;
			}
			
		}
		else{
			redirectAttributes.addFlashAttribute("cek", bookingDao.cek(start, end, idRoom));
			model.addAttribute("localDate", LocalDate.now());
			redirectAttributes.addFlashAttribute("message2","");
			return "redirect:/employee/book/room/"+idr;
			
		}
		
		model.addAttribute("allRoom", roomDao.listRoom());
		model.addAttribute("allBooking", bookingDao.listBooking());
		model.addAttribute("localDate", LocalDate.now());
		Booking b= new Booking(0, id_Employee, idr, start, end, status, about, department, pic, email, noTelepon,room);
		bookingDao.saveOrUpdate(b);
		return "redirect:/home";
		
	}
	

	
	

	@RequestMapping(value="/user/cancel/m/{idBooking}")
	public String myBook(@PathVariable Integer idBooking,HttpServletRequest request,Model model){
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		bookingDao.cancel(idBooking);
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		request.getSession().setAttribute("employee", true);
		return "redirect:/myBooking";
		
	}
	
	@RequestMapping(value="/user/cancel/booking/{idBooking}")
	public String delete(@PathVariable Integer idBooking,HttpServletRequest request,Model model,Booking booking){
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		request.getSession().setAttribute("employee", true);
		 
		Integer id_Employee=((Employee)request.getSession().getAttribute("employeeLogin")).getIdEmployee();
		Booking mb= bookingDao.getIdBooking(idBooking);
		Booking b = new Booking(mb.getIdBooking(), mb.getId_Employee(), mb.getId_Room(), mb.getStart(), mb.getEnd(), "cancel", mb.getAbout(), mb.getDepartment(), mb.getPic(),mb.getEmail(),mb.getNoTelepon(),mb.getRoom());
		bookingDao.saveOrUpdate(b);
		return "redirect:/myBooking";
		
	}
	
	
	
	  

}
