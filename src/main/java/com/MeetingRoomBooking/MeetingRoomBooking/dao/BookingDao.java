package com.MeetingRoomBooking.MeetingRoomBooking.dao;



import java.util.Date;
import java.util.List;

import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;



public interface BookingDao {
	List <Booking> listBooking();
	Booking saveOrUpdate (Booking booking);
	Booking getIdBooking(Integer idBooking);
	void cancel(Integer idBooking);
	List <Booking> cek(Date start, java.util.Date end,Integer idRoom);
	List<Booking> showByDate(String dateHome1);
	List<Booking> byIdEmployee(Integer idEmployee);
	List<Booking> myBook(Integer idEmployee);
	Integer count1();
	Integer count2(String monthAndYear);
	Integer count3(String year);
	Integer countJanuari(String year );
	Integer countFebruari(String year );
	Integer countMaret(String year );
	Integer countApril(String year );
	Integer countMei(String year );
	Integer countJuni(String year );
	Integer countJuli(String year );
	Integer countAgustus(String year );
	Integer countSeptember(String year );
	Integer countOktober(String year );
	Integer countNovember(String year );
	Integer countDesember(String year );
	

}
