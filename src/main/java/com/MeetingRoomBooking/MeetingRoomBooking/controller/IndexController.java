package com.MeetingRoomBooking.MeetingRoomBooking.controller;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MeetingRoomBooking.MeetingRoomBooking.dao.BookingDao;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.EmployeeDao;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.RoomDao;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Employee;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;



@Controller
public class IndexController {
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private Employee employeeLogin;
	
	
	
	
	@RequestMapping("/mrb")
	public String mrb(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes)
	{
		if (request.getSession().getAttribute("eLogin") != null) {
			return "redirect:/home";
		}
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("allRoom", roomDao.listRoom());
		model.addAttribute("allBooking", bookingDao.listBooking());
		//model.addAttribute("sortBooking", bookingDao.orderByStart());
		model.addAttribute("ListBook", bookingDao.listBooking());
		List<Room> Floors =  roomDao.byFloor2();
		model.addAttribute("distinc", Floors);
		
		model.addAttribute("today",true);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String  dateHome1 = dateFormat.format(date); 
		
		
		
		String floor = "0";
		
		if(Floors.get(0) != null) {
			;
			model.addAttribute("All", roomDao.listRoom());
			
			model.addAttribute("allR",true);
			redirectAttributes.addFlashAttribute("All", roomDao.listRoom());
			redirectAttributes.addFlashAttribute("allR",true);
			
		}
		
		
		if(request.getParameter("showByDate2") != null) {
			dateHome1 = request.getParameter("showByDate2");
		}
		if(request.getParameter("floor") != null) {
			floor = request.getParameter("floor");
			model.addAttribute("allR",false);
		}
		
		Integer fl = Integer.parseInt(floor);
		
		model.addAttribute("homeByfloor", roomDao.showByFloor(fl));
		model.addAttribute("homeByDate", bookingDao.showByDate(dateHome1));
		model.addAttribute("selectedFloor", fl);
		model.addAttribute("selectedDate", dateHome1);
		
		
		
		return "Home";
		
		
		
	}
	
	

	@RequestMapping("/home")
	public String index(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes)
	{
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("title", "- Home");
		model.addAttribute("navigation", "Home");
		if ((Boolean) request.getSession().getAttribute("admin") == true) {
			model.addAttribute("admin", true);
		}
		if ((Boolean) request.getSession().getAttribute("employee") == true) {
			model.addAttribute("employee", true);
		}
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/mrb";
		}
		
		Employee employeeLogin = (Employee) request.getSession().getAttribute("eLogin");
		model.addAttribute("nameUser", employeeLogin.getName());
		model.addAttribute("idUser", employeeLogin.getIdEmployee());
		model.addAttribute("role", employeeLogin.getRole());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("allRoom", roomDao.listRoom());
		model.addAttribute("allBooking", bookingDao.listBooking());
		
		//model.addAttribute("sortBooking", bookingDao.orderByStart());
		model.addAttribute("ListBook", bookingDao.listBooking());
		List<Room> Floors =  roomDao.byFloor2();
		model.addAttribute("distinc", Floors);
		redirectAttributes.addFlashAttribute("lBbyDate",false);
		redirectAttributes.addFlashAttribute("lBbyFloor",false);
		model.addAttribute("today",true);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String  dateHome1 = dateFormat.format(date); 
		
		String floor = "0";
		
		if(Floors.get(0) != null) {
			//floor = Floors.get(0).getFloor().toString();
			//redirectAttributes.addFlashAttribute("allRoom", roomDao.listRoom());
			model.addAttribute("All", roomDao.listRoom());
			model.addAttribute("allR",true);
			redirectAttributes.addFlashAttribute("All", roomDao.listRoom());
			redirectAttributes.addFlashAttribute("allR",true);
			
		}
		
		if(request.getParameter("showByDate2") != null) {
			dateHome1 = request.getParameter("showByDate2");
		}
		if(request.getParameter("floor") != null) {
			floor = request.getParameter("floor");
			model.addAttribute("allR",false);
		}
		
		
		Integer fl = Integer.parseInt(floor);
		
		model.addAttribute("homeByfloor", roomDao.showByFloor(fl));
		model.addAttribute("homeByDate", bookingDao.showByDate(dateHome1));
		redirectAttributes.addFlashAttribute("homeByDate", bookingDao.showByDate(dateHome1));
		redirectAttributes.addFlashAttribute("homeByfloor", roomDao.showByFloor(fl));
		model.addAttribute("selectedFloor", fl);
		model.addAttribute("selectedDate", dateHome1);
		
		return "Home";
		
		
	}
	
	

	
	@RequestMapping("/login")
	public String masuk(HttpServletRequest request, Model model)
	{
		if (request.getSession().getAttribute("eLogin") != null) {
			return "redirect:/login";
		}

		model.addAttribute("allRoom", roomDao.listRoom());
		return "login";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		employeeLogin = (Employee) employeeDao.login(username, password);

		if (employeeLogin != null && employeeLogin.getPassword().matches(password))
		{
			request.getSession().setAttribute("employeeLogin", employeeDao.login(username, password));
			
			
			model.addAttribute("userLogin", employeeDao.login(username, password));
			model.addAttribute("username", employeeLogin.getUsername());
			if(employeeLogin.getRole().equals("employee"))
			{	
				request.getSession().setAttribute("employee", true);
				employeeLogin = new Employee(employeeDao.login(username, password).getIdEmployee(), employeeDao.login(username, password).getName(), employeeDao.login(username, password).getDepartment(), employeeDao.login(username, password).getUsername(), employeeDao.login(username, password).getPassword(), employeeDao.login(username, password).getRole(),employeeDao.login(username, password).getEmail(),employeeDao.login(username, password).getRole());
				request.getSession().setAttribute("eLogin", employeeLogin);
				request.getSession().setAttribute("admin", false);
				request.getSession().setAttribute("employee", true);
				return "redirect:/home";
			}
			else if(employeeLogin.getRole().equals("admin"))
			{
				employeeLogin = new Employee(employeeDao.login(username, password).getIdEmployee(), employeeDao.login(username, password).getName(), employeeDao.login(username, password).getDepartment(), employeeDao.login(username, password).getUsername(), employeeDao.login(username, password).getPassword(), employeeDao.login(username, password).getRole(),employeeDao.login(username, password).getEmail(),employeeDao.login(username, password).getRole());
				request.getSession().setAttribute("eLogin", employeeLogin);
				request.getSession().setAttribute("admin", true);
				request.getSession().setAttribute("employee", false);
				return "redirect:/home";
			}

			else{
				redirectAttributes.addFlashAttribute("message",true);
				return "login";
				
			}
		}
		else{
			/*model.addAttribute("message2","Wrong Username or Password.Try again.");*/
			redirectAttributes.addFlashAttribute("message",true);
			return "redirect:/login";
		}
	}

	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("admin", false);
		request.getSession().setAttribute("employee", false);
		request.getSession().invalidate();
		return "redirect:/login";
	}

	@RequestMapping("/myBooking")
	public String myBook(HttpServletRequest request,Booking booking,Model model,RedirectAttributes redirectAttributes) throws ParseException{
		if (request.getSession().getAttribute("eLogin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("nameUser",((Employee)request.getSession().getAttribute("employeeLogin")).getName());
		Integer idEmployee = employeeLogin.getIdEmployee();
		model.addAttribute("ListBookingByAccount", bookingDao.myBook(idEmployee));
		model.addAttribute("idUser", employeeLogin.getIdEmployee());
		model.addAttribute("employee", true);
		model.addAttribute("navigation", "employee");
		
		return "myBook";
	}
	
	@RequestMapping(value="/home/cancel/booking/{idBooking}")
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
		return "redirect:/home";
		
		
	}

}
