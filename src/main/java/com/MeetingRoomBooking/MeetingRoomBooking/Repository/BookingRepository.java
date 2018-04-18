package com.MeetingRoomBooking.MeetingRoomBooking.Repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MeetingRoomBooking.MeetingRoomBooking.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Serializable> {
	LocalDate dateNow = LocalDate.now();
	@Query("SELECT b FROM Booking b  WHERE b.id_Room=:id_Room AND ((b.start <=:start AND b.end>=:start) OR (b.start <=:end AND b.end>=:end)) AND b.status='Booking' ")
	List<Booking> cek(@Param("start") Date start,@Param("end") Date end,@Param("id_Room") Integer idRoom);
	
	/*@Query("Select b From Booking b WHERE b.id_Employee=:idEmployee  ORDER BY b.start")
	List<Booking> myBook(@Param("idEmployee")Integer idEmployee);
*/	
	
	@Query("Select b From Booking b WHERE (SUBSTRING(b.start,1,10)=:dateHome1) ORDER BY b.start")
	List<Booking> showByDate(@Param("dateHome1") String dateHome1);
	
	@Query("Select b From Booking b WHERE b.id_Employee=:idE ORDER BY b.start")
	List<Booking> byIdEmployee(@Param("idE") Integer idEmployee);
	
	@Query("Select b From Booking b WHERE b.start=:today ORDER BY b.start")
	List<Booking> today(@Param("today") Date today);
	@Query("Select b From Booking b")
	List<Booking> listBooking();
	
	@Query("Select count(b) From Booking b")
	Integer count1();
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:monthAndYear) ")
	Integer count2(@Param("monthAndYear") String monthAndYear);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,4)=:year) ")
	Integer count3(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year)")
	Integer januari(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer februari(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer maret(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer april(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer mei(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer juni(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer juli(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer agustus(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer september(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer oktober(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer november(@Param("year") String year);
	
	@Query("Select count(b) From Booking b where (SUBSTRING(b.start,1,7)=:year) ")
	Integer desember(@Param("year") String year);
	
	
}
