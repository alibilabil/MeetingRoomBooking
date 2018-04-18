package com.MeetingRoomBooking.MeetingRoomBooking.implement;





import java.util.List;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingRoomBooking.MeetingRoomBooking.Repository.BookingRepository;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.BookingDao;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;



@Service
public class BookingDaoImpl implements BookingDao {
private EntityManagerFactory emf;

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	public BookingDaoImpl(EntityManagerFactory emf) {

		this.emf = emf;
	}

	@Override
	public List<Booking> listBooking() {
		
		
		return bookingRepository.listBooking();
		
		
	}

	@Override
	public Booking saveOrUpdate(Booking booking) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Booking saved = em.merge(booking);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public Booking getIdBooking(Integer idBooking) {
		EntityManager em = emf.createEntityManager();
		return em.find(Booking.class, idBooking);
	}

	@Override
	public void cancel(Integer idBooking) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Booking.class,idBooking));
		em.getTransaction().commit();   
		
	}

	

	@Override
	public List<Booking> cek(Date start, Date end,Integer idRoom) {
		// TODO Auto-generated method stub
		return bookingRepository.cek(start, end, idRoom);
	}

	/*@Override
	public List<Booking> orderByStart() {
		// TODO Auto-generated method stub
		return bookingRepository.orderByStart();
	}*/




	@Override
	public List<Booking> showByDate(String dateHome1) {
		// TODO Auto-generated method stub
		return bookingRepository.showByDate(dateHome1);
	}

	

	@Override
	public List<Booking> byIdEmployee(Integer idEmployee) {
		
		return bookingRepository.byIdEmployee(idEmployee);
	}

	@Override
	public List<Booking> myBook(Integer idEmployee) {
		EntityManager em = emf.createEntityManager();
		LocalDate date = LocalDate.now();
		return em.createQuery("from Booking where (start >'"+date+"'  and  id_Employee='"+idEmployee+"' )  ", Booking.class).getResultList();
		
	}

	@Override
	public Integer count1() {
		// TODO Auto-generated method stub
		
		return bookingRepository.count1();
	}

	@Override
	public Integer count2(String monthAndYear) {
		// TODO Auto-generated method stub
		return bookingRepository.count2(monthAndYear);
	}

	@Override
	public Integer countJanuari(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.januari(year);
	}

	@Override
	public Integer countFebruari(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.februari(year);
	}

	@Override
	public Integer countMaret(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.maret(year);
	}

	@Override
	public Integer countApril(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.april(year);
	}

	@Override
	public Integer countMei(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.mei(year);
	}

	@Override
	public Integer countJuni(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.juni(year);
	}

	@Override
	public Integer countJuli(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.juli(year);
	}

	@Override
	public Integer countAgustus(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.agustus(year);
	}

	@Override
	public Integer countSeptember(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.september(year);
	}

	@Override
	public Integer countOktober(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.oktober(year);
	}

	@Override
	public Integer countNovember(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.november(year);
	}

	@Override
	public Integer countDesember(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.desember(year);
	}

	@Override
	public Integer count3(String year) {
		// TODO Auto-generated method stub
		return bookingRepository.count3(year);
	}

	
	
	/*public List<Booking> myBook(Integer idEmployee) {
		return bookingRepository.myBook(idEmployee);

	}*/


	
	


	

	
	
	

}
