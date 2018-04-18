package com.MeetingRoomBooking.MeetingRoomBooking.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;



public interface RoomRepository extends JpaRepository<Room, Serializable> {
	@Query("SELECT r FROM Room r ORDER BY r.floor")
	public List<Room> orderById();
	
	@Query("SELECT r FROM Room r ORDER BY r.floor")
	List<Room> listRoom();
	
	@Query("SELECT r FROM Room r where r.floor=:floor")
	public List<Room> byFloor(@Param("floor") Integer floor);
	
	@Query("select r from Room r GROUP by r.floor ")
	public List<Room> byFloor2();

}
